package com.lib.fpm.repository;

import org.springframework.transaction.annotation.Transactional;
import com.lib.fpm.domains.BookType;


@Transactional
public interface BookTypeRepository extends BaseRepository<BookType>{

}
