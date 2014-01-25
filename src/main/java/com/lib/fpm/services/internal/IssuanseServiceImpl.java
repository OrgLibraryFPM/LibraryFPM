package com.lib.fpm.services.internal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lib.fpm.domains.Issuanse;
import com.lib.fpm.repository.IssuanseRepositor;
import com.lib.fpm.services.IssuanseService;

@Service
public class IssuanseServiceImpl extends BaseServiceImpl<Issuanse> implements IssuanseService {
	
	 @Inject
     public void setRepository(IssuanseRepositor repository) {
             this.repository = repository;
     }
}
