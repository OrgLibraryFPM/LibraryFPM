package com.lib.fpm.services.internal;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lib.fpm.domains.Book;
import com.lib.fpm.domains.Book_;
import com.lib.fpm.repository.BookRepository;
import com.lib.fpm.services.BookService;
import com.lib.fpm.util.SpecUtility;

@Service
public class BookServiceImpl extends BaseServiceImpl<Book> implements BookService {
	
	 @Inject
     public void setRepository(BookRepository repository) {
             this.repository = repository;
     }
	 
	 public BookRepository getRepository(){
		 return (BookRepository) repository;
	 }

	@Override
	public List<Book> getByLike(String likeValue) {
		return getRepository().findAll(where(likeName(likeValue))
				.or(likeIsbn(likeValue)));
	}
	
	private final Specification<Book> likeName(final String likeValue) {
		return SpecUtility.createStringLike(Book_.name, likeValue);
	}
	
	private final Specification<Book> likeIsbn(final String likeValue) {
		return SpecUtility.createStringLike(Book_.isbn, likeValue);
	}
}
