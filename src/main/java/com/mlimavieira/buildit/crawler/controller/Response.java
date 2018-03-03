package com.mlimavieira.buildit.crawler.controller;

public class Response {

	private Integer currentPage;
	private Long totalElements;

	private Object result;

	public Response() {

	}

	public Response(Integer currentPage, Long totalElements, Object result) {
		super();
		this.currentPage = currentPage;
		this.totalElements = totalElements;
		this.result = result;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
