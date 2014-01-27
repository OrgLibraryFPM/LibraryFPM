package com.lib.fpm.rest;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.lib.fpm.domains.Issuanse;
import com.lib.fpm.services.IssuanseService;

@Component
@Path("/issuanse")
public class IssuanseRestService extends BaseRestService<Issuanse>  {
	
	 @Inject
     public void setService(IssuanseService service) {
             this.service = service;
     }
}
