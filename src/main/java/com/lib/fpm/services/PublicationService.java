package com.lib.fpm.services;

import java.util.List;

import com.lib.fpm.domains.Publication;

public interface PublicationService extends BaseService<Publication> {
	
	List<Publication> getByLike(String likeValue);

}
