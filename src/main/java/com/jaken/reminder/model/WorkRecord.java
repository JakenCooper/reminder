package com.jaken.reminder.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="t_work_record")
public class WorkRecord {

	@Id
	@Column(name="id")
	private String id;
	@Column(name="title")
	private String title;
	@Column(name="allo_year")
	private Integer alloYear;
	@Column(name="allo_month")
	private Integer alloMonth;
	@Column(name="allo_day")
	private Integer alloDay;
	@Column(name="allo_date")
	private Date alloDate;
	@Column(name="allo_date_time")
	private Date alloDateTime;
	@Column(name="content")
	private String content;
	@Column(name="star")
	private Integer star;
	
	
	public WorkRecord() {
		super();
	}
	
	
	public WorkRecord(String id) {
		super();
		this.id = id;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAlloYear() {
		return alloYear;
	}
	public void setAlloYear(Integer alloYear) {
		this.alloYear = alloYear;
	}
	public Integer getAlloMonth() {
		return alloMonth;
	}
	public void setAlloMonth(Integer alloMonth) {
		this.alloMonth = alloMonth;
	}
	public Integer getAlloDay() {
		return alloDay;
	}
	public void setAlloDay(Integer alloDay) {
		this.alloDay = alloDay;
	}
	public Date getAlloDate() {
		return alloDate;
	}
	public void setAlloDate(Date alloDate) {
		this.alloDate = alloDate;
	}
	public Date getAlloDateTime() {
		return alloDateTime;
	}
	public void setAlloDateTime(Date alloDateTime) {
		this.alloDateTime = alloDateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
}
