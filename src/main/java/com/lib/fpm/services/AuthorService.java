package com.lib.fpm.services;

import java.util.List;

import com.lib.fpm.domains.Author;

public interface AuthorService extends BaseService<Author> {
	
	List<Author> getByLike(String likeValue);

}
