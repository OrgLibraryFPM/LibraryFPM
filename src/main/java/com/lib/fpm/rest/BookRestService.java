package com.lib.fpm.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.lib.fpm.domains.Book;
import com.lib.fpm.services.BookService;

@Component
@Path("/book")
public class BookRestService extends BaseRestService<Book>  {
	
	 @Inject
     public void setService(BookService service) {
             this.service = service;
     }
}
