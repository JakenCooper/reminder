package com.jaken.reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jaken.reminder.model.WorkRecord;
import com.jaken.reminder.service.WorkRecordService;

@Controller
public class WorkRecordController {

	@Autowired
	private WorkRecordService wrs; 
	
	@RequestMapping(value="/workrecord/findCurmonthAll")
	public String getLastestMonthWorkRecord(Model model) {
		model.addAttribute("reqResult", wrs.getLastestMonthWorkRecord());
		return "workrecord_part";
	}
	
	@RequestMapping(value="/workrecord/addWorkRecord")
	public ResponseEntity<ReqResult<String>> addWorkRecord(RequestEntity<WorkRecord> recordEntity){
		WorkRecord record=recordEntity.getBody();
		ReqResult<String> reqResult=new ReqResult<String>();
		reqResult.setSuccess(true);
		reqResult.setMessage("success");
		ResponseEntity<ReqResult<String>> result=new ResponseEntity<ReqResult<String>>(reqResult,HttpStatus.OK);
		if(StringUtils.isEmpty(record.getTitle())&&StringUtils.isEmpty(record.getContent())) {
			reqResult.setSuccess(false);
			reqResult.setMessage("empty content!");
			return result;
		}
		try {
			wrs.addWorkRecord(record);
		} catch (Exception e) {
			e.printStackTrace();
			reqResult.setSuccess(false);
			reqResult.setMessage("exception occurs during operation!");
		}
		return result;
	}
	
	@RequestMapping(value="/workrecord/del/{recordId}")
	public ResponseEntity<ReqResult<String>> deleteWorkRecord(@PathVariable(value="recordId") String recordId){
		ReqResult<String> result=new ReqResult<String>();
		result.setSuccess(true);
		result.setMessage("success");
		ResponseEntity<ReqResult<String>> response=new ResponseEntity<ReqResult<String>>(result,HttpStatus.OK);
		if(StringUtils.isEmpty(recordId)) {
			result.setSuccess(false);
			result.setMessage("empty id");
			return response;
		}
		try {
			wrs.deleteWorkRecord(new WorkRecord(recordId));
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("error...");
		}
		return response;
	}
}
