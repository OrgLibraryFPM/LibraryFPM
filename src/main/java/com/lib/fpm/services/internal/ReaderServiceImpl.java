package com.lib.fpm.services.internal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lib.fpm.domains.Reader;
import com.lib.fpm.repository.ReaderRepository;
import com.lib.fpm.services.ReaderService;

@Service
public class ReaderServiceImpl extends BaseServiceImpl<Reader> implements ReaderService {
	
	 @Inject
     public void setRepository(ReaderRepository repository) {
             this.repository = repository;
     }
}
