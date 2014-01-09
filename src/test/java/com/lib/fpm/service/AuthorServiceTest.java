package com.lib.fpm.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.lib.fpm.domains.Author;
import com.lib.fpm.services.AuthorService;

public class AuthorServiceTest extends PersistenceTest{
	
	private static String LAST_NAME = "Zuzukin";
	private static String FIRST_NAME = "Sasha";
	private static String MIDDLE_NAME = "Ivanovych";
	
	@Inject
	private AuthorService authorService;

	@Test
	public void testCreate(){
		Author author = new Author();
		author.setLastName(LAST_NAME);
		author.setFirstName(FIRST_NAME);
		author.setMiddleName(MIDDLE_NAME);
		
		Author authorTest = authorService.create(author);
		assertNotNull(authorTest);
	}
	
	@Test
	public void testCreateList(){
		Integer size = 3;
		List<Author> authors = new ArrayList<Author>();
		for (int i = 0; i < size; i++) {
			Author author = new Author();
			author.setLastName(LAST_NAME+i);
			author.setFirstName(FIRST_NAME);
			author.setMiddleName(MIDDLE_NAME);
			authors.add(author);
		}
		List<Author> listTest = authorService.createByList(authors);
		assertNotNull(listTest);
		assertEquals(3, listTest.size());
	}
	
	@Test
	public void testUpdateList(){
		Integer size = 2;
		List<Author> authors = new ArrayList<Author>();
		for (int i = 1; i <= size; i++) {
			Author author = authorService.findById(Long.valueOf(i));
			author.setFirstName(FIRST_NAME+i);
			authors.add(author);
		}
		List<Author> listTest = authorService.updateByList(authors);
		assertNotNull(listTest);
		assertEquals(2, listTest.size());
	}
	
	@Test
	public void testUpdate(){
		Author author = authorService.findById(1L);
		author.setMiddleName(MIDDLE_NAME);
		
		Author test = authorService.update(author);
		assertNotNull(test);
		assertEquals(MIDDLE_NAME, test.getMiddleName());
	}
	
	@Test
	public void testDelete(){
		authorService.delete(1L);
		Author author = authorService.findById(1L);
		assertNull(author);
	}
	
	@Test
	public void testFindById(){
		Author author = authorService.findById(1L);
		assertNotNull(author);
		assertEquals("Pupkin",author.getLastName());
	}
	
	@Test
	public void testFindAll(){
		List<Author> authors = authorService.findAll();
		assertNotNull(authors);
		assertEquals(2,authors.size());
	}
	
	@Test
	public void testCount(){
		Long count = authorService.count();
		assertEquals(Long.valueOf(2),count);
	}
}
