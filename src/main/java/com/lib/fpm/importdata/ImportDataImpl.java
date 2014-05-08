package com.lib.fpm.importdata;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	private static final String TABLE_BOOK = "Книга2";
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void importData(byte[] data) throws IOException, ClassNotFoundException, SQLException{
		File dataBase = File.createTempFile("dataBaseLIB", "accdb");
		Connection con = null;
		try{
			FileUtils.writeByteArrayToFile(dataBase, data);
			String path = dataBase.getAbsolutePath();
		    String db ="JDBC:ODBC:Driver=Microsoft Access Driver (*.mdb, *.accdb); DBQ="+path;
//		    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//		    Class<T> c = sun.jdbc.odbc.Jdbc
		    Properties conInfo = new Properties();
	        conInfo.put("user", "");
	        conInfo.put("password","");
	        conInfo.put("charSet", "Cp1251");
            con = DriverManager.getConnection(db, conInfo);
            
            cleanDB();
            Map<Long, BookType> bookTypes = importBookType(con);
            importBook(con, bookTypes);
		} finally{
			if (con!=null){
				con.close();
			}
			dataBase.deleteOnExit();
		}
	}
	
	private Map<Long, BookType> importBookType(Connection con) throws SQLException{
		Statement st = con.createStatement();
        String query = "select * from " + TABLE_TYPE;
		ResultSet rs = st.executeQuery(query);
		Map<Long, BookType> data = new HashMap<Long, BookType>();
        while(rs.next()){
        	BookType bookType = new BookType();
        	bookType.setType(rs.getString(2));
        	bookType = bookTypeService.create(bookType);
        	data.put(rs.getLong(1), bookType);
        }
        return data;
	}
	
	private void importBook(Connection con, Map<Long, BookType> bookTypes) throws SQLException{
		Statement st = con.createStatement();
        String query = "select * from " + TABLE_BOOK;
		ResultSet rs = st.executeQuery(query);
        while(rs.next()){
        	Book book = new Book();
        	book.setName(rs.getString(3));
        	book.setBookType(bookTypes.get(rs.getLong(4)));
        	book.setYear(rs.getInt(5));
        	book.setTome(rs.getInt(6));
        	book.setAuthors(parseAuthor(rs.getString(7)));
        	book.setPart(rs.getInt(8));
        	book.setSeries(rs.getInt(9));
        	book.setPageCount(rs.getInt(10));
        	book.setNumber(rs.getInt(11));
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
	private void cleanDB(){
		issuanseRepository.deleteAll();
		bookRepository.deleteAll();
		authorRepository.deleteAll();
		bookTypeRepository.deleteAll();
	}
}
