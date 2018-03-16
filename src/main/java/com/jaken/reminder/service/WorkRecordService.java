package com.jaken.reminder.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaken.reminder.controller.ReqResult;
import com.jaken.reminder.dao.WorkRecordDao;
import com.jaken.reminder.model.WorkRecord;
import com.jaken.reminder.model.WorkRecordWrapper;
import com.jaken.reminder.util.CommonUtils;

@Service
@Transactional
public class WorkRecordService {
	
	@Autowired
	private WorkRecordDao workRecordDao;
	
	//static page arguemnts,query twice.
	public ReqResult<WorkRecordWrapper> getLastestMonthWorkRecord(){
		ReqResult<WorkRecordWrapper> reqResult=new ReqResult<WorkRecordWrapper>();
		Sort sort=new Sort(Direction.DESC, "alloDateTime");
		Pageable pageable1=new PageRequest(0, 1);
		Page<WorkRecord> page1= workRecordDao.findAll(pageable1);
		if(page1.getContent().size()==0) {
			reqResult.setSuccess(true);
			reqResult.setMessage("empty results");
			return reqResult;
		}
		final WorkRecord fr=page1.getContent().get(0);
		// all records in current month.
		Pageable pageable2=new PageRequest(0,40,sort);
		Specification<WorkRecord> spec=new Specification<WorkRecord>() {
			@Override
			public Predicate toPredicate(Root<WorkRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1=cb.equal(root.get("alloYear"), fr.getAlloYear());
				Predicate p2=cb.equal(root.get("alloMonth"),fr.getAlloMonth());
				return cb.and(p1,p2);
			}
		};
		Page<WorkRecord> finalPage=workRecordDao.findAll(spec,pageable2);
		reqResult.setSuccess(true);
		reqResult.setCurrentPage(0);
		reqResult.setPageSize(40);
		reqResult.setMaxRecords(new Long(finalPage.getTotalElements()).intValue());
		reqResult.setMaxPages(finalPage.getTotalPages());
		reqResult.setResult(new WorkRecordWrapper(fr.getAlloYear(), fr.getAlloMonth(), 
				finalPage.getContent().size(), finalPage.getContent()));
		return reqResult;
	}
	
	public void addWorkRecord(WorkRecord wr) {
		wr.setId(CommonUtils.getDbUUid());
		Date curdate=new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(curdate);
		wr.setAlloYear(cal.get(Calendar.YEAR));
		wr.setAlloMonth(cal.get(Calendar.MONTH)+1);
		wr.setAlloDay(cal.get(Calendar.DAY_OF_MONTH));
		wr.setAlloDate(curdate);
		wr.setAlloDateTime(curdate);
		wr.setStar(0);
		workRecordDao.save(wr);
	}
	
	public void deleteWorkRecord(WorkRecord wr) {
		if(wr==null||wr.getId()==null) {
			throw new IllegalArgumentException("内容为空，无法删除");
		}
		workRecordDao.delete(wr);
	}
}