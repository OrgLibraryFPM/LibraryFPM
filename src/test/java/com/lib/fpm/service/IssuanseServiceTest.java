package com.lib.fpm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import com.lib.fpm.domains.Book;
import com.lib.fpm.domains.Issuanse;
import com.lib.fpm.services.BookService;
import com.lib.fpm.services.IssuanseService;
import com.lib.fpm.services.ReaderService;

public class IssuanseServiceTest extends PersistenceTest{
	
	@Inject
	private IssuanseService issuanseService;
	
	@Inject
	private BookService bookService;
	
	@Inject
	private ReaderService readerService;

	@Test
	public void testCreate(){
		Calendar calendarIss = Calendar.getInstance();
		calendarIss.set(2014, 0, 25);
		
		Calendar calendarRet = Calendar.getInstance();
		calendarRet.set(2014, 0, 30);
		Issuanse issuanse = new Issuanse();
		issuanse.setDateIssuanse(calendarIss.getTime());
		issuanse.setDateIssuanse(calendarRet.getTime());
		issuanse.setBook(bookService.findById(1L));
		issuanse.setReader(readerService.findById(1L));
		
		Issuanse test = issuanseService.create(issuanse);
		assertNotNull(test);
	}
	
	@Test
	public void testCreateList(){
		Integer size = 3;
		List<Issuanse> issuanses = new ArrayList<Issuanse>();
		for (int i = 0; i < size; i++) {
			Issuanse issuanse = new Issuanse();
			issuanse.setBook(bookService.findById(1L));
			issuanse.setReader(readerService.findById(2L));
			issuanse.setDateIssuanse(new Date());
			issuanses.add(issuanse);
		}
		List<Issuanse> listTest = issuanseService.createByList(issuanses);
		assertNotNull(listTest);
		assertEquals(3, listTest.size());
	}
	
	@Test
	public void testUpdateList(){
		Integer size = 2;
		List<Issuanse> issuanses = new ArrayList<Issuanse>();
		for (int i = 1; i <= size; i++) {
			Issuanse issuanse = issuanseService.findById(Long.valueOf(i));
			issuanse.setDateIssuanse(new Date());
			issuanses.add(issuanse);
		}
		List<Issuanse> listTest = issuanseService.updateByList(issuanses);
		assertNotNull(listTest);
		assertEquals(2, listTest.size());
	}
	
	@Test
	public void testUpdate(){
		Issuanse issuanse = issuanseService.findById(1L);
		Book book = bookService.findById(1L);
		issuanse.setBook(book);
		
		Issuanse test = issuanseService.update(issuanse);
		assertNotNull(test);
		assertEquals(book, test.getBook());
	}
	
	@Test
	public void testDelete(){
		try {
			issuanseService.delete(1L);
		} catch (DataIntegrityViolationException e) {
		}
	}	
	@Test
	public void testFindById(){
		Issuanse issuanse = issuanseService.findById(1L);
		assertNotNull(issuanse);
	}
	
	@Test
	public void testFindAll(){
		List<Issuanse> issuanses = issuanseService.findAll();
		assertNotNull(issuanses);
		assertEquals(2,issuanses.size());
	}
	
	@Test
	public void testCount(){
		Long count = issuanseService.count();
		assertEquals(Long.valueOf(2),count);
	}
}
