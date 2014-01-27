package com.lib.fpm.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.lib.fpm.domains.Author;
import com.lib.fpm.services.AuthorService;

@Component
@Path("/author")
public class AuthorRestService extends BaseRestService<Author>  {
	
	 @Inject
     public void setService(AuthorService service) {
             this.service = service;
     }
}
