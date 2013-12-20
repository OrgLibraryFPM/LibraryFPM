package com.lib.fpm.services.internal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lib.fpm.domains.BookType;
import com.lib.fpm.repository.BookTypeRepository;
import com.lib.fpm.services.BookTypeService;

@Service
public class BookTypeServiceImpl extends BaseServiceImpl<BookType> implements BookTypeService {
	
	 @Inject
     public void setRepository(BookTypeRepository repository) {
             this.repository = repository;
     }
}
