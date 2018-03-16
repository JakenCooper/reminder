package com.jaken.reminder.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jaken.reminder.model.Article;

@Repository("articleDao")
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

	@Modifying
	@Query(value = "update t_article set last_allo_date=:lastAlloDate,expect_allo_date=:expectAlloDate,allo_times=:alloTimes,expire=:expire where id=:articleId", nativeQuery = true)
	public void updateArticleAllo(@Param("lastAlloDate") Date lastAlloDate, @Param("expectAlloDate") Date expectAlloDate, 
			@Param("alloTimes") Integer alloTimes, @Param("expire") Integer expire,
			@Param("articleId") String articleId);
}
