package com.lib.fpm.services.internal;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lib.fpm.domains.BookType;
import com.lib.fpm.domains.BookType_;
import com.lib.fpm.repository.BookTypeRepository;
import com.lib.fpm.services.BookTypeService;
import com.lib.fpm.util.SpecUtility;

@Service
public class BookTypeServiceImpl extends BaseServiceImpl<BookType> implements BookTypeService {
	
	 @Inject
     public void setRepository(BookTypeRepository repository) {
             this.repository = repository;
     }
	 
	 public BookTypeRepository getRepository(){
		 return (BookTypeRepository) repository;
	 }

	@Override
	public List<BookType> getByLike(String likeValue) {
		return getRepository().findAll(likeType(likeValue));
	}
	
	private final Specification<BookType> likeType(final String likeValue) {
		return SpecUtility.createStringLike(BookType_.type, likeValue);
	}
}
