package com.lib.fpm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.lib.fpm.domains.Author;


@Transactional
public interface AuthorRepository extends BaseRepository<Author>{
	
	@Transactional
	@Query("SELECT a.id FROM Author a "
			+ "WHERE a.lastName = (?1) AND "
			+ "a.firstName = (?2) AND "
			+ "a.middleName=(?3)")
	Long isExistAuthor(String lastName, String firstName, String middleName);

}
