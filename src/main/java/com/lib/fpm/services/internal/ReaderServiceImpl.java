package com.lib.fpm.services.internal;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lib.fpm.domains.Reader;
import com.lib.fpm.domains.Reader_;
import com.lib.fpm.repository.ReaderRepository;
import com.lib.fpm.services.ReaderService;
import com.lib.fpm.util.SpecUtility;

@Service
public class ReaderServiceImpl extends BaseServiceImpl<Reader> implements ReaderService {

	@Inject
	public void setRepository(ReaderRepository repository) {
		this.repository = repository;
	}
	
	public ReaderRepository getRepository(){
		return (ReaderRepository) repository;
	}
	
	@Override
	public List<Reader> getByLike(String likeValue) {
		return getRepository().findAll(where(likeFirstName(likeValue))
				.or(likeLastName(likeValue))
				.or(likeMiddleName(likeValue)));
	}
	
	private final Specification<Reader> likeFirstName(final String likeValue) {
		return SpecUtility.createStringLike(Reader_.firstName, likeValue);
	}
	
	private final Specification<Reader> likeLastName(final String likeValue) {
		return SpecUtility.createStringLike(Reader_.lastName, likeValue);
	}
	
	private final Specification<Reader> likeMiddleName(final String likeValue) {
		return SpecUtility.createStringLike(Reader_.middleName, likeValue);
	}
}
