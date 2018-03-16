package com.jaken.reminder.controller;

import java.util.List;

public class ReqResult<T> {

	private boolean success;
	private String message;
	
	private List<String> errorField;
	private List<String> errorMessages;
	
	private T result;
	
	private Integer currentPage;
	private Integer pageSize;
	
	private Integer maxRecords;
	private Integer maxPages;
	
	
	public ReqResult() {
		super();
	}
	
	
	public ReqResult(boolean success, String message, List<String> errorField, List<String> errorMessages) {
		super();
		this.success = success;
		this.message = message;
		this.errorField = errorField;
		this.errorMessages = errorMessages;
	}


	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getErrorField() {
		return errorField;
	}
	public void setErrorField(List<String> errorField) {
		this.errorField = errorField;
	}
	public List<String> getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getMaxRecords() {
		return maxRecords;
	}
	public void setMaxRecords(Integer maxRecords) {
		this.maxRecords = maxRecords;
	}
	public Integer getMaxPages() {
		return maxPages;
	}
	public void setMaxPages(Integer maxPages) {
		this.maxPages = maxPages;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
}
