package com.jaken.reminder.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jaken.reminder.model.ArticleRecord;

@Repository("articleRecordDao")
public interface ArticleRecordDao extends JpaRepository<ArticleRecord, String>,JpaSpecificationExecutor<ArticleRecord>{

	public Integer countArticleRecordByAlloDate(Date date);

	public List<ArticleRecord> findArticleRecordByArticleId(String articleId);
	
	public List<ArticleRecord> findArticleRecordByAlloDate(Date date);
	
	@Modifying
	@Query(value="delete from t_article_record where articleId=:articleId",nativeQuery=true)
	public void deleteArticleRecordByArticleId(@Param("articleId") String articleId);
}
