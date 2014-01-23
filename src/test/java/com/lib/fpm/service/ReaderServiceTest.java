package com.lib.fpm.service;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.lib.fpm.domains.Reader;
import com.lib.fpm.services.ReaderService;

public class ReaderServiceTest extends PersistenceTest{
	
	private static String LAST_NAME = "Zuzukin";
	private static String FIRST_NAME = "Sasha";
	private static String MIDDLE_NAME = "Ivanovych";
	
	@Inject
	private ReaderService readerService;
	
	@Test
	public void testCreate(){
		Reader reader = new Reader();
		reader.setLastName(LAST_NAME);
		reader.setFirstName(FIRST_NAME);
		reader.setMiddleName(MIDDLE_NAME);
		
		Reader test = readerService.create(reader);
		assertNotNull(test);
	}
	
	@Test
	public void testCreateList(){
		Integer size = 3;
		List<Reader> readers = new ArrayList<Reader>();
		for (int i = 0; i < size; i++) {
			Reader reader = new Reader();
			reader.setLastName(LAST_NAME+i);
			reader.setFirstName(FIRST_NAME);
			reader.setMiddleName(MIDDLE_NAME);
			readers.add(reader);
		}
		List<Reader> listTest = readerService.createByList(readers);
		assertNotNull(listTest);
		assertEquals(3, listTest.size());
	}
	
	@Test
	public void testUpdateList(){
		Integer size = 2;
		List<Reader> readers = new ArrayList<Reader>();
		for (int i = 1; i <= size; i++) {
			Reader reader = readerService.findById(Long.valueOf(i));
			reader.setFirstName(FIRST_NAME+i);
			readers.add(reader);
		}
		List<Reader> listTest = readerService.updateByList(readers);
		assertNotNull(listTest);
		assertEquals(2, listTest.size());
	}
	
	@Test
	public void testUpdate(){
		Reader reader = readerService.findById(1L);
		reader.setMiddleName(MIDDLE_NAME);
		
		Reader test = readerService.update(reader);
		assertNotNull(test);
		assertEquals(MIDDLE_NAME, test.getMiddleName());
	}
	
	@Test
	public void testDelete(){
		readerService.delete(1L);
		Reader reader = readerService.findById(1L);
		assertThat(reader, nullValue());
	}
	
	@Test
	public void testFindById(){
		Reader reader = readerService.findById(1L);
		assertNotNull(reader);
		assertEquals("Shutka",reader.getLastName());
	}
	
	@Test
	public void testFindAll(){
		List<Reader> readers = readerService.findAll();
		assertNotNull(readers);
		assertEquals(3,readers.size());
	}
	
	@Test
	public void testCount(){
		Long count = readerService.count();
		assertEquals(Long.valueOf(3),count);
	}
}
