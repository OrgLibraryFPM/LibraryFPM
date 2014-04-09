package com.lib.fpm.services;

import java.util.List;

import com.lib.fpm.domains.Reader;

public interface ReaderService extends BaseService<Reader> {
	
	List<Reader> getByLike(String likeValue);

}
