package com.lib.fpm.domains;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name="authors")
@XmlRootElement
public class Author extends Account {
	
	private List<Book> books;
	
	public Author() {
		super();
	}
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
	@JsonBackReference
	public List<Book> getBooks() {
		return books;
	}

	@JsonBackReference
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.appendSuper(super.hashCode())
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
