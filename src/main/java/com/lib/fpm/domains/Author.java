package com.lib.fpm.domains;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name="authors")
public class Author extends Account{
	
	private List<Book> books;
	
	public Author() {
		super();
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(super.hashCode())
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
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.isEquals();
	}
}
