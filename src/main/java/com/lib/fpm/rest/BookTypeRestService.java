package com.lib.fpm.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.lib.fpm.domains.BookType;
import com.lib.fpm.services.BookTypeService;

@Component
@Path("/bookType")
public class BookTypeRestService extends BaseRestService<BookType>  {
	
	 @Inject
     public void setService(BookTypeService service) {
             this.service = service;
     }
}
