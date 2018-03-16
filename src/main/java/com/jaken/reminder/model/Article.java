package com.jaken.reminder.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity(name="t_article")
//@Table(name="t_article")
public class Article {

	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="name")
	@NotNull(message="{name.notnull}")
	private String name;
	
	@Column(name="detail")
	@NotNull(message="{detail.notnull}") 
	private String detail;
	
	@Column(name="allo_date")
	private Date alloDate;
	
	@Column(name="last_allo_date")
	private Date lastAlloDate;

	@Column(name="expect_allo_date")
	private Date expectAlloDate;
	
	@Column(name="allo_times")
	private Integer alloTimes;

	@Column(name="mature")
	private Integer mature;
	
	@Column(name="article_category_id")
	private String categoryId;
	
	@Column(name="expire")
	private Integer expire;
	
	
	@Transient
	private String actionType;
	
	public Article() {
		super();
	}

	public Article(String id, String name, String detail, Date alloDate, Date lastAlloDate, Date expectAlloDate,
			Integer alloTimes, Integer mature, String categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.detail = detail;
		this.alloDate = alloDate;
		this.lastAlloDate = lastAlloDate;
		this.expectAlloDate = expectAlloDate;
		this.alloTimes = alloTimes;
		this.mature = mature;
		this.categoryId = categoryId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Integer getExpire() {
		return expire;
	}

	public void setExpire(Integer expire) {
		this.expire = expire;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getAlloDate() {
		return alloDate;
	}

	public void setAlloDate(Date alloDate) {
		this.alloDate = alloDate;
	}

	public Date getLastAlloDate() {
		return lastAlloDate;
	}

	public void setLastAlloDate(Date lastAlloDate) {
		this.lastAlloDate = lastAlloDate;
	}

	public Integer getAlloTimes() {
		return alloTimes;
	}

	public void setAlloTimes(Integer alloTimes) {
		this.alloTimes = alloTimes;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public Date getExpectAlloDate() {
		return expectAlloDate;
	}

	public Integer getMature() {
		return mature;
	}

	public void setMature(Integer mature) {
		this.mature = mature;
	}

	public void setExpectAlloDate(Date expectAlloDate) {
		this.expectAlloDate = expectAlloDate;
	}
}
