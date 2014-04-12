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
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="books")
@XmlRootElement
public class Book extends IdDomain {
	
	private String name;
	private Integer year;
	private String isbn;
	private String note;
	private BookType bookType;
	private Publication publication;
	private List<Author> authors;
	private List<Issuanse> issuanses;
	private Integer tome;
	private Integer number;
	private Integer part;
	private Integer series;
	private Integer pageCount;
	
	public Book() {
		super();
	}

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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="author_book",
			joinColumns = @JoinColumn(name="book_id", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name="author_id", referencedColumnName="id")
	)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "books")
	@JsonIgnore
	public List<Issuanse> getIssuanses() {
		return issuanses;
	}

	public void setIssuanses(List<Issuanse> issuanses) {
		this.issuanses = issuanses;
	}

	@Column(name="tome")
	public Integer getTome() {
		return tome;
	}

	public void setTome(Integer tome) {
		this.tome = tome;
	}

	@Column(name="number")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name="part")
	public Integer getPart() {
		return part;
	}

	public void setPart(Integer part) {
		this.part = part;
	}

	@Column(name="series")
	public Integer getSeries() {
		return series;
	}

	public void setSeries(Integer series) {
		this.series = series;
	}

	@Column(name="page_count")
	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(name)
				.append(year)
				.append(isbn)
				.append(note)
				.append(bookType)
				.append(publication)
				.append(tome)
				.append(number)
				.append(part)
				.append(series)
				.append(pageCount)
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
				.append(tome, other.tome)
				.append(number, other.number)
				.append(part, other.part)
				.append(series, other.series)
				.append(pageCount, other.pageCount)
				.isEquals();
	}
}
