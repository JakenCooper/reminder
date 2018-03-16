package com.jaken.reminder.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jaken.reminder.model.Article;
import com.jaken.reminder.model.ArticleRecord;
import com.jaken.reminder.service.ArticleRecordService;
import com.jaken.reminder.service.ArticleService;

@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleRecordService articleRecordService;
	
	@RequestMapping(value="/article/add",method=RequestMethod.POST)
	public String addArticle(@Valid Article article,Errors errors,Model model) {
		if(errors.getErrorCount()>0) {
			List<String> errorField=new ArrayList<String>();
			List<String> errorMessages=new ArrayList<String>();
			List<ObjectError> errorlist=errors.getAllErrors();
			for(ObjectError error:errorlist) {
				errorField.add(error.getObjectName());
				errorMessages.add(error.getDefaultMessage());
			}
			model.addAttribute("reqResult", new ReqResult<>(false, "validation error", errorField, errorMessages));
			return "redirect:/main";
		}
		articleService.addArticle(article);
		model.addAttribute("reqResult",articleService.findAllArticlesByPage(0, 40));
		return "redirect:/main";
	}
	
	@RequestMapping(value="/article/modify",method=RequestMethod.POST)
	public String modifyArticle(Article article,Model model) {
		articleService.updateArticle(article);
		model.addAttribute("reqResult",articleService.findAllArticlesByPage(0, 40));
		return "redirect:/main";
	}
	
	@RequestMapping(value="/article/del",method=RequestMethod.POST)
	public String delArticle(Article article,Model model) {
		articleService.deleteArticle(article);
		model.addAttribute("reqResult",articleService.findAllArticlesByPage(0, 40));
		return "redirect:/main";
	}
	
	@RequestMapping(value="/articleRecord/add")
	public String addArticleRecord(ArticleRecord articleRecord,Model model) {
		articleRecordService.addArticleRecord(articleRecord);
		model.addAttribute("reqResult",articleService.findAllArticlesByPage(0, 40));
		return "redirect:/main";
	}
	
	@RequestMapping(value="/articleRecord/findAll")
	public String getAllArticleRecords(RequestEntity<ArticleRecord> entity,Model model) {
		model.addAttribute("reqResult", articleRecordService.getAllArticleRecordByArticleId(entity.getBody().getArticleId(),
				0, 10));
		return "tabpart";
	}
}
