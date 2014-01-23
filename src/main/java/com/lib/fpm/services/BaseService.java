package com.lib.fpm.services;

import java.util.List;

public interface BaseService<E> {
	
	public E create(E entity);
	
	public E update(E entity);
	
	public List<E> createByList(List<E> entitys);
	
	public List<E> updateByList(List<E> entitys);
	
	public Boolean delete(Long id);
	
	public List<E> findAll();
	
	public E findById(Long id);
	
	public Long count();
}