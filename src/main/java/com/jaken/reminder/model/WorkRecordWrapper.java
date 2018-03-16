package com.jaken.reminder.model;

import java.util.List;

public class WorkRecordWrapper {

	private Integer year;
	
	private Integer month;
	
	private Integer totalsize;
	
	private List<WorkRecord> recordList;

	
	public WorkRecordWrapper(Integer year, Integer month, Integer totalsize, List<WorkRecord> recordList) {
		super();
		this.year = year;
		this.month = month;
		this.totalsize = totalsize;
		this.recordList = recordList;
	}
	
	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(Integer totalsize) {
		this.totalsize = totalsize;
	}

	public List<WorkRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<WorkRecord> recordList) {
		this.recordList = recordList;
	}
	
}
