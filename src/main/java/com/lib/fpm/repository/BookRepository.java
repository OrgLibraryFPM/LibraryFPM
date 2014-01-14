package com.lib.fpm.repository;

import org.springframework.transaction.annotation.Transactional;

import com.lib.fpm.domains.Book;


@Transactional
public interface BookRepository extends BaseRepository<Book>{

}
