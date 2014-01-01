package com.lib.fpm.services.internal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lib.fpm.domains.Publication;
import com.lib.fpm.repository.PublicationRepository;
import com.lib.fpm.services.PublicationService;

@Service
public class PublicationServiceImpl extends BaseServiceImpl<Publication> implements PublicationService {
	
	 @Inject
     public void setRepository(PublicationRepository repository) {
             this.repository = repository;
     }
}
