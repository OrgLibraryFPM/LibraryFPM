package com.lib.fpm.services;

import java.util.List;

import com.lib.fpm.domains.BookType;

public interface BookTypeService extends BaseService<BookType> {
	
	List<BookType> getByLike(String likeValue);

}
