package com.lib.fpm.repository;

import javax.inject.Inject;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.lib.fpm.service.PersistenceTest;

public class AuthorRepositoryTest extends PersistenceTest {

	@Inject
	private AuthorRepository authorRepository;
	
	@Test
	public void testIsAuthors(){
		Long id = authorRepository.isExistAuthor("Pupkin", "Vasa", "Vasyljvych");
		assertThat(id, is(1L));
		
		id = authorRepository.isExistAuthor("Pupkin", "V", "Vasyljvych");
		assertThat(id, nullValue());
	}
}
