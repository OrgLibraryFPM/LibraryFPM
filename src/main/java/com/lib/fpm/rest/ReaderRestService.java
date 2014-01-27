package com.lib.fpm.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.lib.fpm.domains.Reader;
import com.lib.fpm.services.ReaderService;

@Component
@Path("/reader")
public class ReaderRestService extends BaseRestService<Reader>  {
	
	 @Inject
     public void setService(ReaderService service) {
             this.service = service;
     }
}
