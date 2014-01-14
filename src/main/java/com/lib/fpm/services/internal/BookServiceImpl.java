package com.lib.fpm.services.internal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lib.fpm.domains.Book;
import com.lib.fpm.repository.BookRepository;
import com.lib.fpm.services.BookService;

@Service
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {
	
	 @Inject
     public void setRepository(BookRepository repository) {
             this.repository = repository;
     }
}
