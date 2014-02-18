package com.lib.fpm.pagination;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class Page {
	
	public static final String DEFAULT_SORT_FIELD = "id";
	public static final String DEFAULT_SORT_TYPE = "asc";
	
	private Integer pageNumber;
	private Integer countRowsInOnePage;
	private String fieldSort;
	private String sortType;
	
	public Page(Integer pageNumber, Integer countRowsInOnePage,
			String fieldSort, String sortType) {
		super();
		
		if (pageNumber == null || pageNumber <= 0) {
			pageNumber = 1;
		}

		if (countRowsInOnePage == null || countRowsInOnePage < 0) {
			countRowsInOnePage = Integer.MAX_VALUE;
		}

		if (StringUtils.isBlank(fieldSort)) {
			fieldSort = DEFAULT_SORT_FIELD;
		}

		if (StringUtils.isBlank(sortType)) {
			sortType = DEFAULT_SORT_TYPE;
		}
		
		this.pageNumber = pageNumber-1;
		this.countRowsInOnePage = countRowsInOnePage;
		this.fieldSort = fieldSort;
		this.sortType = sortType;
	}	
	
	public Pageable toPageable(){
		Order order = new Order(sortType.equals("asc") ? Direction.ASC: Direction.DESC, fieldSort);
		return new PageRequest(pageNumber, countRowsInOnePage, new Sort(order));
	}

}
