package com.lib.fpm.repository;

import org.springframework.transaction.annotation.Transactional;

import com.lib.fpm.domains.Author;


@Transactional
public interface AuthorRepository extends BaseRepository<Author>{

}
