package com.lib.fpm.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.lib.fpm.domains.Book;
import com.lib.fpm.services.BookService;

@Component
@Path("/book")
public class BookRestService extends BaseRestService<Book> {

	@Inject
	public void setService(BookService service) {
		this.service = service;
	}

	public BookService getService() {
		return (BookService) service;
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> findByLike(@QueryParam("term") String likeValue) {
		return getService().getByLike(likeValue);
	}
}
