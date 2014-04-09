package com.lib.fpm.domains;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "issuanses")
@XmlRootElement
public class Issuanse extends IdDomain {

	private Date dateIssuanse;
	private Date dateReturn;
	private Reader reader;
	private List<Book> books;

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

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="reader_id")
	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="book_issuanse",
			joinColumns = @JoinColumn(name="issuanse_id", referencedColumnName="id"),
	        inverseJoinColumns = @JoinColumn(name="book_id", referencedColumnName="id")
	)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
				.append(dateIssuanse)
				.append(dateReturn)
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
				.append(reader, other.reader)
				.isEquals();
	}
}
