package com.lib.fpm.services.internal;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.lib.fpm.domains.Publication;
import com.lib.fpm.domains.Publication_;
import com.lib.fpm.repository.PublicationRepository;
import com.lib.fpm.services.PublicationService;
import com.lib.fpm.util.SpecUtility;

@Service
public class PublicationServiceImpl extends BaseServiceImpl<Publication> implements PublicationService {
	
	 @Inject
     public void setRepository(PublicationRepository repository) {
             this.repository = repository;
     }
	 
	 public PublicationRepository getRepository(){
		 return (PublicationRepository) repository;
	 }

	@Override
	public List<Publication> getByLike(String likeValue) {
		return getRepository().findAll(likeType(likeValue));
	}
	
	private final Specification<Publication> likeType(final String likeValue) {
		return SpecUtility.createStringLike(Publication_.name, likeValue);
	}
}
