package com.lib.fpm.importdata;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.lib.fpm.domains.Author;
import com.lib.fpm.domains.Book;
import com.lib.fpm.domains.BookType;
import com.lib.fpm.repository.AuthorRepository;
import com.lib.fpm.repository.BookRepository;
import com.lib.fpm.repository.BookTypeRepository;
import com.lib.fpm.repository.IssuanseRepository;
import com.lib.fpm.services.BookService;
import com.lib.fpm.services.BookTypeService;

@Service
public class ImportDataImpl implements ImportData {
	
	@Inject
	private BookTypeService bookTypeService;
	
	@Inject
	private BookService bookService;
	
	@Inject
	private BookTypeRepository bookTypeRepository;
	
	@Inject
	private BookRepository bookRepository;
	
	@Inject
	private IssuanseRepository issuanseRepository;
	
	@Inject
	private AuthorRepository authorRepository;
	
	private static final String TABLE_TYPE = "Тип";
	private static final String TABLE_TYPE_NAME = "наименование";
	private static final String TABLE_TYPE_ID = "Код";
	
	private static final String TABLE_BOOK = "Книга2";
	private static final String TABLE_BOOK_NAME = "Назва";
	private static final String TABLE_BOOK_TYPE = "Тип";
	private static final String TABLE_BOOK_YEAR = "Рік";
	private static final String TABLE_BOOK_TOME = "Том";
	private static final String TABLE_BOOK_AUTHORS = "Автори";
	private static final String TABLE_BOOK_PART = "Частина";
	private static final String TABLE_BOOK_SERIES = "Серія";
	private static final String TABLE_BOOK_PAGE_COUNT = "Кількість сторінок";
	private static final String TABLE_BOOK_NUMBER = "номер";
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void importData(byte[] data) throws IOException, ClassNotFoundException, SQLException{
		File dataBaseFile = File.createTempFile("dataBaseLIB", "accdb");
		Database database = null;
		try{
			FileUtils.writeByteArrayToFile(dataBaseFile, data);
			database = DatabaseBuilder.open(dataBaseFile);
            
            cleanDB();
            Map<Long, BookType> bookTypes = importBookType(database);
            importBook(database, bookTypes);
		} finally{
			if (database!=null){
				database.close();
			}
			dataBaseFile.deleteOnExit();
		}
	}
	
	private Map<Long, BookType> importBookType(Database database) throws SQLException, IOException{
		Map<Long, BookType> data = new HashMap<Long, BookType>();
		Table table = database.getTable(TABLE_TYPE);
		for (Row row : table) {
			BookType bookType = new BookType();
        	bookType.setType((String)row.get(TABLE_TYPE_NAME));
        	bookType = bookTypeService.create(bookType);
        	data.put(Long.valueOf((Integer)row.get(TABLE_TYPE_ID)), bookType);
		}
        return data;
	}
	
	private void importBook(Database database, Map<Long, BookType> bookTypes) throws SQLException, IOException{
		Table table = database.getTable(TABLE_BOOK);
		for (Row row : table) {
        	Book book = new Book();
        	book.setName((String) row.get(TABLE_BOOK_NAME));
        	book.setBookType(bookTypes.get(Long.valueOf(parseInt((String)row.get(TABLE_BOOK_TYPE)))));
        	book.setYear((Integer) row.get(TABLE_BOOK_YEAR));
        	book.setTome(parseInt((String)row.get(TABLE_BOOK_TOME)));
        	book.setAuthors(parseAuthor((String)row.get(TABLE_BOOK_AUTHORS)));
        	book.setPart(parseInt((String)row.get(TABLE_BOOK_PART)));
        	book.setSeries(parseInt((String)row.get(TABLE_BOOK_SERIES)));
        	book.setPageCount((Integer)row.get(TABLE_BOOK_PAGE_COUNT));
        	book.setNumber(parseInt((String)row.get(TABLE_BOOK_NUMBER)));
        	bookService.create(book);
        }
	}
	
	private List<Author> parseAuthor(String authors){
		List<Author> result = new ArrayList<Author>();
		if (StringUtils.isNotBlank(authors)){
			String[] data = authors.split(",");
			for (int i = 0; i < data.length; i++) {
				String[] author = data[i].trim().split("\\s|\\.");
				Author authorEntity = new Author();
				if (author.length>=1){
					authorEntity.setLastName(author[0]);
				}
				if (author.length>=2){
					authorEntity.setFirstName(author[1]);
				}
				if (author.length>=3){
					authorEntity.setMiddleName(author[2]);
				}
				Long authorId = authorRepository.isExistAuthor(authorEntity.getLastName(), authorEntity.getFirstName(), authorEntity.getMiddleName());
				if (authorId!=null){
					authorEntity = authorRepository.findOne(authorId);
				}
				result.add(authorEntity);
			}
		}
		return result;
	}
	
	private Integer parseInt(String number){
		if (StringUtils.isBlank(number)){
			return null;
		}
		return Integer.valueOf(number);
	}
	private void cleanDB(){
		issuanseRepository.deleteAll();
		bookRepository.deleteAll();
		authorRepository.deleteAll();
		bookTypeRepository.deleteAll();
	}
}
