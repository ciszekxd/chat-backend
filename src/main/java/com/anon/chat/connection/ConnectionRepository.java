package com.anon.chat.connection;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ConnectionRepository extends CrudRepository<ConnectionDto, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE ConnectionDto c SET c.active = false where c.id = :id")
    void setInactive(@Param("id") Long id);
}