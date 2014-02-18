package com.lib.fpm.pagination;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PageJqGrid<E> {
	
	private Integer total;							// total pages
	private Integer page;							// current page
	private Long records;							// total number of records
	private List<E> rows;							// an array that contains the actual data

	public PageJqGrid(Integer currentPage, Long totalCountRecords, Integer countRowsInOnePage,
			List<E> data) {
		super();
		this.total = (int) Math.ceil((double) totalCountRecords / countRowsInOnePage);
		this.page = currentPage;
		this.records = totalCountRecords;
		this.rows = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}

}
