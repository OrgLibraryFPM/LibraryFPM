package com.lib.fpm.repository;

import org.springframework.transaction.annotation.Transactional;

import com.lib.fpm.domains.Publication;


@Transactional
public interface PublicationRepository extends BaseRepository<Publication>{

}
