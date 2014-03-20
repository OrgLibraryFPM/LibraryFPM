package com.lib.fpm.services.internal;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lib.fpm.domains.Author;
import com.lib.fpm.domains.Author_;
import com.lib.fpm.repository.AuthorRepository;
import com.lib.fpm.services.AuthorService;
import com.lib.fpm.util.SpecUtility;

@Service
public class AuthorServiceImpl extends BaseServiceImpl<Author> implements AuthorService {
	
	 @Inject
     public void setRepository(AuthorRepository repository) {
             this.repository = repository;
     }
	 
	 public AuthorRepository getRepository(){
		 return (AuthorRepository) repository;
	 }

	@Override
	public List<Author> getByLike(String likeValue) {
		return getRepository().findAll(where(likeFirstName(likeValue))
											.or(likeLastName(likeValue))
											.or(likeMiddleName(likeValue)));
	}
	
	private final Specification<Author> likeFirstName(final String likeValue) {
		return SpecUtility.createStringLike(Author_.firstName, likeValue);
	}
	
	private final Specification<Author> likeLastName(final String likeValue) {
		return SpecUtility.createStringLike(Author_.lastName, likeValue);
	}
	
	private final Specification<Author> likeMiddleName(final String likeValue) {
		return SpecUtility.createStringLike(Author_.middleName, likeValue);
	}
}
