package com.lib.fpm.services.internal;

import java.util.List;
import org.springframework.stereotype.Service;

import com.lib.fpm.repository.BaseRepository;
import com.lib.fpm.services.BaseService;

@Service
public class BaseServiceImpl<E> implements BaseService<E> {
	
	protected BaseRepository<E> repository;

	@Override
	public E create(E entity) {
		return repository.save(entity);
	}

	@Override
	public E update(E entity) {
		return repository.saveAndFlush(entity);
	}
	
	@Override
	public List<E> createByList(List<E> entitys) {
		return repository.save(entitys);
	}

	@Override
	public List<E> updateByList(List<E> entitys) {
		return repository.save(entitys);
	}
	
	@Override
	public Boolean delete(Long id) {
		if(id!=null)
			repository.delete(id);
		else
			return false;
		return !repository.exists(id);
	}

	@Override
	public List<E> findAll() {
		return repository.findAll();
	}

	@Override
	public E findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Long count() {
		return repository.count();
	}
}
