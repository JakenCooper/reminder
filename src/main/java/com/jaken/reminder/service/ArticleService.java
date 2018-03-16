package com.jaken.reminder.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jaken.reminder.controller.ReqResult;
import com.jaken.reminder.dao.ArticleDao;
import com.jaken.reminder.model.Article;
import com.jaken.reminder.util.CommonUtils;

@Transactional
@Service
public class ArticleService{

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ArticleRecordService articleRecordService;
	
	
	public Article getArticleById(String id) {
		return articleDao.findOne(id);
	}
	
	/**
	 * 只适合基础主题更新（页面上更新名称以及描述字段），并不适合归档更新
	 * */
	public void updateArticle(Article article) {
		Article testarticle=articleDao.findOne(article.getId());
		Article readyArticle=new Article();
		BeanUtils.copyProperties(testarticle, readyArticle);
		readyArticle.setName(article.getName());
		readyArticle.setDetail(article.getDetail());
		articleDao.delete(article);
		articleDao.save(readyArticle);
	}
	
	
	public void deleteArticle(Article article) {
		String articleId=article.getId();
		articleDao.delete(article);
		//级联删除
		articleRecordService.deleteArticleRecordByArticleId(articleId);
	}
	
	public void updateArticleAllo(Article article) {
		articleDao.updateArticleAllo(new Date(), getExceptAlloDate(new Date(), article.getAlloTimes()+1), article.getAlloTimes()+1, 0, article.getId());
	}
	
	
	
	public ReqResult<List<Article>> findAllArticlesByPage(Integer currpage,Integer pagesize){
		Sort expireSort=new Sort(Direction.DESC,"expire");
		Sort finalSort=expireSort.and(new Sort(Direction.ASC,"expectAlloDate"));
		Pageable pageable=new PageRequest(currpage, pagesize,finalSort);
		Page<Article> page=articleDao.findAll(pageable);
		ReqResult<List<Article>> result=new ReqResult<List<Article>>();
		result.setSuccess(true);
		result.setCurrentPage(currpage);
		result.setPageSize(pagesize);
		result.setMaxRecords(new Long(page.getTotalElements()).intValue());
		result.setMaxPages(page.getTotalPages());
		result.setResult(page.getContent());
		return result;
	}
	
	
	
	public void addArticle(Article article) {
		article.setId(CommonUtils.getDbUUid());
		Date curdate=new Date();
		article.setAlloDate(curdate);
		article.setLastAlloDate(curdate);
		article.setAlloTimes(1);
		article.setExpectAlloDate(getExceptAlloDate(article.getAlloDate(), article.getAlloTimes()));
		article.setMature(0);
		article.setExpire(0);
		articleDao.save(article);
	}
	
	//算法：except=2*(times-1)+1
	public Date getExceptAlloDate(Date beginDate,int times) {
		int days=2*(times-1)+1;
		if(days<=0) {
			days=1;
		}
		return CommonUtils.dateAdd(beginDate, days);
	}
	
	
}
