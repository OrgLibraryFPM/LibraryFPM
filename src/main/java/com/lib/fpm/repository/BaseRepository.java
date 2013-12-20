package com.lib.fpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface BaseRepository<E> extends JpaRepository<E, Long>{

}
