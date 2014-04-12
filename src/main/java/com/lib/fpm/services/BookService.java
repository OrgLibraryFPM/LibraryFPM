package com.lib.fpm.services;

import java.util.List;

import com.lib.fpm.domains.Book;

public interface BookService extends BaseService<Book> {

	List<Book> getByLike(String likeValue);
	
}
