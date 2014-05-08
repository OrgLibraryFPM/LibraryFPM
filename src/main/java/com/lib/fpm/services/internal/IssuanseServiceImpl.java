package com.lib.fpm.services.internal;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.lib.fpm.domains.Issuanse;
import com.lib.fpm.exceptions.DontDeleteRecordException;
import com.lib.fpm.repository.IssuanseRepository;
import com.lib.fpm.services.IssuanseService;

@Service
public class IssuanseServiceImpl extends BaseServiceImpl<Issuanse> implements IssuanseService {
	
	 @Inject
     public void setRepository(IssuanseRepository repository) {
             this.repository = repository;
     }
	 
	@Override
	public Boolean delete(Long id) throws DontDeleteRecordException {
		if (id != null){
			Issuanse issuanse = findById(id);
			if (issuanse != null && issuanse.getDateReturn() !=null ){
				repository.delete(id);
			}else{
				throw new DontDeleteRecordException("This issuance can not be removed because the books are not returned");
			}
		}else{
			return false;
		}
		return !repository.exists(id);
	}
}
