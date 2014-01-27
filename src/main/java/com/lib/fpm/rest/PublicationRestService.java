package com.lib.fpm.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.lib.fpm.domains.Publication;
import com.lib.fpm.services.PublicationService;

@Component
@Path("/publication")
public class PublicationRestService extends BaseRestService<Publication>  {
	
	 @Inject
     public void setService(PublicationService service) {
             this.service = service;
     }
}
