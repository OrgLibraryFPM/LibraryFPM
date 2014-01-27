package com.lib.fpm.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.lib.fpm.services.BaseService;

@Component
public class BaseRestService<E> {
	
	protected BaseService<E> service;
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<E> findAll(){
		return service.findAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public E findById(@PathParam("id") final Long id){
		return service.findById(id);
	}
	
	@POST
	@Path("/createOne")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public E create(E entity){
		return service.create(entity);
	}
	
	@POST
	@Path("/createList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<E> createList(List<E> entitys){
		return service.createByList(entitys);
	}
	
	@PUT
	@Path("/updateOne")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public E update(E entity){
		return service.update(entity);
	}
	
	@PUT
	@Path("/updateList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<E> updateList(List<E> entitys){
		return service.updateByList(entitys);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean delete(@PathParam("id") final Long id){
		return service.delete(id);
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.APPLICATION_JSON)
	public Long count(){
		return service.count();
	}
}
