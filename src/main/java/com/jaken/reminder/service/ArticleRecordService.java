package com.jaken.reminder.service;

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
import com.jaken.reminder.dao.ArticleDao;
import com.jaken.reminder.dao.ArticleRecordDao;
import com.jaken.reminder.model.Article;
import com.jaken.reminder.model.ArticleRecord;
import com.jaken.reminder.util.CommonUtils;

@Transactional
@Service
public class ArticleRecordService {

	@Autowired
	private ArticleRecordDao articleRecordDao;
	

	@Autowired
	private ArticleDao articleDao;
	
	
	@Autowired
	private ArticleService articleService;
	/*
	 * 同一天内多次归档不会影响主题的归档次数
	 * */
	public void addArticleRecord(ArticleRecord record) {
		Date curdate=new Date();
		if(!record.isJumpRecord()) {
			record.setId(CommonUtils.getDbUUid());
			record.setAlloDate(curdate);
			record.setAlloDateTime(curdate);
			articleRecordDao.save(record);
		}
//		Date rdate=CommonUtils.getCurrentDate();
//		System.out.println(CommonUtils.formatDate(rdate));
//		Integer rcount=articleRecordDao.countArticleRecordByAlloDate(rdate);
//		System.out.println(rcount);
//		if(rcount>0) {
//			return ;
//		}
		Article article=articleDao.findOne(record.getArticleId());
		if(article==null) {
			throw new IllegalArgumentException("主题为空！");
		}
		articleDao.updateArticleAllo(curdate, articleService.getExceptAlloDate(curdate, article.getAlloTimes()+1), article.getAlloTimes()+1,
				0, article.getId());
	}
	
	
	public void deleteArticleRecordByArticleId(String articleId) {
		articleRecordDao.deleteArticleRecordByArticleId(articleId);
	}
	
	public ReqResult<List<ArticleRecord>> getAllArticleRecordByArticleId(final String articleId,Integer currpage,Integer pagesize){
		Sort alloSort=new Sort(Direction.DESC,"alloDateTime");
		Pageable pageable=new PageRequest(currpage,pagesize,alloSort);
		Specification<ArticleRecord> spec=new Specification<ArticleRecord>() {
			@Override
			public Predicate toPredicate(Root<ArticleRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate p1=cb.equal(root.get("articleId"), articleId);
				return p1;
			}
		};
		Page<ArticleRecord> page=articleRecordDao.findAll(spec, pageable);
		ReqResult<List<ArticleRecord>> result=new ReqResult<List<ArticleRecord>>();
		result.setSuccess(true);
		result.setCurrentPage(currpage);
		result.setPageSize(pagesize);
		result.setMaxRecords(new Long(page.getTotalElements()).intValue());
		result.setMaxPages(page.getTotalPages());
		result.setResult(page.getContent());
		return result;
	}
}
