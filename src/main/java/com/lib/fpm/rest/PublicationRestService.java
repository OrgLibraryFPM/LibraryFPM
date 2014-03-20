package com.lib.fpm.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
	
	public PublicationService getService(){
		return (PublicationService) service;
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Publication> findAll(@QueryParam("term") String likeValue) {
		return getService().getByLike(likeValue);
	}
}
