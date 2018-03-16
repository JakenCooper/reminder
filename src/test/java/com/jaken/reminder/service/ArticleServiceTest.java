package com.jaken.reminder.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jaken.reminder.config.RootConfig;
import com.jaken.reminder.dao.ArticleRecordDao;
import com.jaken.reminder.model.Article;
import com.jaken.reminder.util.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RootConfig.class)
public class ArticleServiceTest {

	@Autowired
	private ApplicationContext ac;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleRecordDao ard;
	
//	@Test 
	//PASS
	public void addarticle() {
		Article article=new Article();
		article.setId(CommonUtils.getDbUUid());
		article.setName("test");
		article.setDetail("test");
		article.setAlloDate(new Date());
		article.setAlloTimes(0);
		articleService.addArticle(article);
	}
	
//	@Test
	//PASS
	public void testupdatearticleallo() {
		List<Article> articles=articleService.findAllArticlesByPage(0, 10).getResult();
		Article article=articles.get(0);
		if(article==null) {return;}
		articleService.updateArticleAllo(article);
	}
	
//	@Test
	//PASS
	public void testupdatearticle() {
		List<Article> articles=articleService.findAllArticlesByPage(0, 10).getResult();
		Article article=articles.get(0);
		article.setName("This is a test name.");
		articleService.updateArticle(article);
	}
	
//	@Test
	public void testrepeatallo() {
//		System.out.println(ard.countArticleRecordByAlloDate(new Date()));
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
//		cal.set(2018,2,1,0,0,0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(CommonUtils.formatDate(cal.getTime()));
		try {
			//		System.out.println(ard.findArticleRecordByArticleId("1812e62a-6954-4c50-8f0e-87afa7dd").size());
			System.out.println(ard.countArticleRecordByAlloDate(cal.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testallodate() {
		Date date=CommonUtils.getCurrentDate();
		Integer count=ard.countArticleRecordByAlloDate(date);
		System.out.println(count);
	}
	
}
