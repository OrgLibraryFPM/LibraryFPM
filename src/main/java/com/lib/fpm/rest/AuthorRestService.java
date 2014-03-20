package com.lib.fpm.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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

	public AuthorService getService() {
		return (AuthorService) service;
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Author> findAll(@QueryParam("term") String likeValue) {
		return getService().getByLike(likeValue);
	}
}
