package com.lib.fpm.domains;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "issuanses")
public class Issuanse extends IdDomain {

	private Date dateIssuanse;
	private Date dateReturn;
	private Book book;
	private Reader reader;

	public Issuanse() {
		super();
	}

	@Column(name = "date_issuanse")
	public Date getDateIssuanse() {
		return dateIssuanse;
	}

	public void setDateIssuanse(Date dateIssuanse) {
		this.dateIssuanse = dateIssuanse;
	}

	@Column(name = "date_return")
	public Date getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}

	@OneToOne
	@JoinColumn(name="book_id")
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@OneToOne
	@JoinColumn(name="reader_id")
	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(dateIssuanse)
				.append(dateReturn)
				.append(book)
				.append(reader).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issuanse other = (Issuanse) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(dateIssuanse, other.dateIssuanse)
				.append(dateReturn, other.dateReturn)
				.append(book, other.book)
				.append(reader, other.reader)
				.isEquals();
	}
}
