package com.lib.fpm.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.lib.fpm.domains.BookType;
import com.lib.fpm.exceptions.DontDeleteRecordException;
import com.lib.fpm.services.BookService;
import com.lib.fpm.services.BookTypeService;

public class BookTypeServiceTest extends PersistenceTest{
	
	@Inject
	private BookTypeService bookTypeService;
	
	@Inject
	private BookService bookService;

	@Test
	public void testCreate(){
		BookType bookType = new BookType();
		bookType.setType("Book");
		
		BookType bookTypeTest = bookTypeService.create(bookType);
		assertNotNull(bookTypeTest);
	}
	
	@Test
	public void testCreateList(){
		Integer size = 3;
		List<BookType> bookTypes = new ArrayList<BookType>();
		for (int i = 0; i < size; i++) {
			BookType bookType = new BookType();
			bookType.setType("book"+i);
			bookTypes.add(bookType);
		}
		List<BookType> listTest = bookTypeService.createByList(bookTypes);
		assertNotNull(listTest);
		assertEquals(3, listTest.size());
	}
	
	@Test
	public void testUpdateList(){
		Integer size = 2;
		List<BookType> bookTypes = new ArrayList<BookType>();
		for (int i = 1; i <= size; i++) {
			BookType bookType = bookTypeService.findById(Long.valueOf(i));
			bookType.setType("book"+i);
			bookTypes.add(bookType);
		}
		List<BookType> listTest = bookTypeService.updateByList(bookTypes);
		assertNotNull(listTest);
		assertEquals(2, listTest.size());
	}
	
	@Test
	public void testUpdate(){
		String newBookType = "new";
		BookType bookType = bookTypeService.findById(1L);
		bookType.setType(newBookType);
		
		BookType test = bookTypeService.update(bookType);
		assertNotNull(test);
		assertEquals(newBookType, test.getType());
	}
	
	@Test
	public void testDelete() throws DontDeleteRecordException{
		bookTypeService.delete(1L);
		BookType bookType = bookTypeService.findById(1L);
		assertNull(bookType);
		assertNull(bookService.findById(1L).getBookType());
	}
	
	@Test
	public void testFindById(){
		BookType bookType = bookTypeService.findById(1L);
		assertNotNull(bookType);
		assertEquals("book",bookType.getType());
	}
	
	@Test
	public void testFindAll(){
		List<BookType> bookTypes = bookTypeService.findAll();
		assertNotNull(bookTypes);
		assertEquals(2,bookTypes.size());
	}
	
	@Test
	public void testCount(){
		Long count = bookTypeService.count();
		assertEquals(Long.valueOf(2),count);
	}
	
	@Test
	public void testGetByLike(){
		List<BookType> list = bookTypeService.getByLike("bo");
		
		assertThat(list, notNullValue());
		assertThat(list.size(), is(1));
		assertThat(list.get(0).getType(), is("book"));
		
		list = bookTypeService.getByLike("oo");
		
		assertThat(list, notNullValue());
		assertThat(list.size(), is(1));
		assertThat(list.get(0).getType(), is("book"));
		
		list = bookTypeService.getByLike("v");
		
		assertThat(list, notNullValue());
		assertThat(list.size(), is(0));
		
	}
}
