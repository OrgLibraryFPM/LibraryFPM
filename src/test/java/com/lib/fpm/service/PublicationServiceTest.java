package com.lib.fpm.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.lib.fpm.domains.Publication;
import com.lib.fpm.services.BookService;
import com.lib.fpm.services.PublicationService;

public class PublicationServiceTest extends PersistenceTest{
	
	@Inject
	private PublicationService publicationService;
	
	@Inject
	private BookService bookService;

	@Test
	public void testCreate(){
		Publication publication = new Publication();
		publication.setName("Wiljams");
		publication.setCity("Kiev");
		
		Publication publicationtest = publicationService.create(publication);
		assertNotNull(publicationtest);
	}
	
	@Test
	public void testCreateList(){
		Integer size = 3;
		List<Publication> publications = new ArrayList<Publication>();
		for (int i = 0; i < size; i++) {
			Publication publication = new Publication();
			publication.setName("name"+i);
			publications.add(publication);
		}
		List<Publication> listTest = publicationService.createByList(publications);
		assertNotNull(listTest);
		assertEquals(3, listTest.size());
	}
	
	@Test
	public void testUpdateList(){
		Integer size = 2;
		List<Publication> publications = new ArrayList<Publication>();
		for (int i = 1; i <= size; i++) {
			Publication publication = publicationService.findById(Long.valueOf(i));
			publication.setName("name"+i);
			publications.add(publication);
		}
		List<Publication> listTest = publicationService.updateByList(publications);
		assertNotNull(listTest);
		assertEquals(2, listTest.size());
	}
	
	@Test
	public void testUpdate(){
		String newPublicationName = "newName";
		Publication publication = publicationService.findById(1L);
		publication.setName(newPublicationName);
		
		Publication test = publicationService.update(publication);
		assertNotNull(test);
		assertEquals(newPublicationName, test.getName());
	}
	
	@Test
	public void testDelete(){
		publicationService.delete(1L);
		Publication publication = publicationService.findById(1L);
		assertNull(publication);
		assertNull(bookService.findById(1L).getPublication());
	}
	
	@Test
	public void testFindById(){
		Publication publication = publicationService.findById(1L);
		assertNotNull(publication);
		assertEquals("simvol-plus",publication.getName());
		assertEquals("peterburg",publication.getCity());
	}
	
	@Test
	public void testFindAll(){
		List<Publication> cityList = publicationService.findAll();
		assertNotNull(cityList);
		assertEquals(2,cityList.size());
	}
	
	@Test
	public void testCount(){
		Long count = publicationService.count();
		assertEquals(Long.valueOf(2),count);
	}
}
