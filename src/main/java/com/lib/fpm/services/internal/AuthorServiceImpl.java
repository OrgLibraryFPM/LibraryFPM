package com.lib.fpm.services.internal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lib.fpm.domains.Author;
import com.lib.fpm.repository.AuthorRepository;
import com.lib.fpm.services.AuthorService;

@Service
public class AuthorServiceImpl extends BaseServiceImpl<Author> implements AuthorService {
	
	 @Inject
     public void setRepository(AuthorRepository repository) {
             this.repository = repository;
     }
}
