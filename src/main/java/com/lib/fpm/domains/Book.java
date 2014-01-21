package com.lib.fpm.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name="books")
public class Book extends IdDomain{
	
	private String name;
	private Integer year;
	private String isbn;
	private String note;
	private BookType bookType;
	private Publication publication;
	private List<Author> authors;
	
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="year")
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name="isbn")
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name="note")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="book_type_id")
	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="publication_id")
	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}
	
	@ManyToMany
	@JoinTable(name="author_book",
			joinColumns = @JoinColumn(name="book_id", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name="author_id", referencedColumnName="id")
	)
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name)
				.append(year)
				.append(isbn)
				.append(note)
				.append(bookType)
				.append(publication)
				.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(name, other.name)
				.append(year, other.year)
				.append(isbn, other.isbn)
				.append(note, other.note)
				.append(bookType, other.bookType)
				.append(publication, other.publication)
				.isEquals();
	}
}
