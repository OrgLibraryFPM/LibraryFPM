package com.lib.fpm.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.lib.fpm.domains.BookType;
import com.lib.fpm.services.BookTypeService;

public class BookTypeServiceTest extends PersistenceTest{
	
	@Inject
	private BookTypeService bookTypeService;

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
	public void testDelete(){
		bookTypeService.delete(1L);
		BookType bookType = bookTypeService.findById(1L);
		assertNull(bookType);
	}
	
	@Test
	public void testFindById(){
		BookType bookType = bookTypeService.findById(1L);
		assertNotNull(bookType);
		assertEquals("book",bookType.getType());
	}
	
	@Test
	public void testFindAll(){
		List<BookType> cityList = bookTypeService.findAll();
		assertNotNull(cityList);
		assertEquals(2,cityList.size());
	}
	
	@Test
	public void testCount(){
		Long count = bookTypeService.count();
		assertEquals(Long.valueOf(2),count);
	}
}
