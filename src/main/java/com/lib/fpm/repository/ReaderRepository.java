package com.lib.fpm.repository;

import org.springframework.transaction.annotation.Transactional;

import com.lib.fpm.domains.Reader;


@Transactional
public interface ReaderRepository extends BaseRepository<Reader>{

}
