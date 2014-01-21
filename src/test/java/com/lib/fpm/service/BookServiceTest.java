package com.lib.fpm.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.lib.fpm.domains.Book;
import com.lib.fpm.services.AuthorService;
import com.lib.fpm.services.BookService;
import com.lib.fpm.services.BookTypeService;
import com.lib.fpm.services.PublicationService;

public class BookServiceTest extends PersistenceTest{
	
	private static final String BOOK_NAME = "Professional Java JDK 6 Edition";
	private static final String BOOK_ISBN = "1548765-652-6985";
	
	@Inject
	private BookService bookService;
	
	@Inject
	private BookTypeService bookTypeService;
	
	@Inject
	private PublicationService publicationService;
	
	@Inject
	private AuthorService authorService;
	
	@Test
	public void testCreate(){
		Book book = new Book();
		book.setName(BOOK_NAME);
		book.setIsbn(BOOK_ISBN);
		book.setYear(2013);
		book.setBookType(bookTypeService.findById(1L));
		book.setPublication(publicationService.findById(1L));
		
		Book authorTest = bookService.create(book);
		assertNotNull(authorTest);
	}
	
	@Test
	public void testCreateList(){
		Integer size = 3;
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < size; i++) {
			Book book = new Book();
			book.setName(BOOK_NAME);
			book.setIsbn(BOOK_ISBN);
			book.setYear(2013);
			book.setBookType(bookTypeService.findById(1L));
			book.setPublication(publicationService.findById(1L));
			
			books.add(book);
		}
		List<Book> listTest = bookService.createByList(books);
		assertNotNull(listTest);
		assertEquals(3, listTest.size());
	}
	
	@Test
	public void testUpdateList(){
		Integer size = 2;
		List<Book> books = new ArrayList<Book>();
		for (int i = 1; i <= size; i++) {
			Book book = bookService.findById(Long.valueOf(i));
			book.setName(BOOK_NAME);
			book.setPublication(publicationService.findById(1L));
			books.add(book);
		}
		List<Book> listTest = bookService.updateByList(books);
		assertNotNull(listTest);
		assertEquals(2, listTest.size());
	}
	
	@Test
	public void testUpdate(){
		Book book = bookService.findById(1L);
		book.setName(BOOK_NAME);
		book.setPublication(publicationService.findById(1L));
		
		Book test = bookService.update(book);
		assertNotNull(test);
		assertEquals(BOOK_NAME, test.getName());
	}
	
	@Test
	public void testDelete(){
		bookService.delete(1L);
		Book book = bookService.findById(1L);
		assertNull(book);
		assertEquals(Long.valueOf(2), publicationService.count());
		assertEquals(Long.valueOf(2), bookTypeService.count());
		assertNotNull(authorService.findById(1L));
	}
	
	@Test
	public void testFindById(){
		Book book = bookService.findById(1L);
		assertNotNull(book);
		assertEquals("Effective Java: Programming Language Guide",book.getName());
		assertNotNull(book.getBookType());
	}
	
	@Test
	public void testFindAll(){
		List<Book> books = bookService.findAll();
		assertNotNull(books);
		assertEquals(3,books.size());
	}
	
	@Test
	public void testCount(){
		Long count = bookService.count();
		assertEquals(Long.valueOf(3),count);
	}
}
