package com.jaken.reminder.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name="t_article_record")
public class ArticleRecord {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="title")
	private String title;
	
	@Column(name="allo_date")
	private Date alloDate;
	
	@Column(name="allo_date_time")
	private Date alloDateTime;
	
	@Column(name="allo_result")
	private Integer alloResult;
	@Column(name="detail")
	private String detail;
	@Column(name="article_id")
	private String articleId;
	
	@Transient
	private boolean jumpRecord;

	public boolean isJumpRecord() {
		return jumpRecord;
	}

	public void setJumpRecord(boolean jumpRecord) {
		this.jumpRecord = jumpRecord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getAlloDate() {
		return alloDate;
	}

	public void setAlloDate(Date alloDate) {
		this.alloDate = alloDate;
	}

	public Integer getAlloResult() {
		return alloResult;
	}

	public void setAlloResult(Integer alloResult) {
		this.alloResult = alloResult;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public Date getAlloDateTime() {
		return alloDateTime;
	}

	public void setAlloDateTime(Date alloDateTime) {
		this.alloDateTime = alloDateTime;
	}
}
