package com.lib.fpm.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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

	public BookTypeService getService() {
		return (BookTypeService) service;
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<BookType> findAll(@QueryParam("term") String likeValue) {
		return getService().getByLike(likeValue);
	}
}
