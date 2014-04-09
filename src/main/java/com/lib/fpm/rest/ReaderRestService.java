package com.lib.fpm.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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

	public ReaderService getService() {
		return (ReaderService) service;
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reader> findAll(@QueryParam("term") String likeValue) {
		return getService().getByLike(likeValue);
	}
}
