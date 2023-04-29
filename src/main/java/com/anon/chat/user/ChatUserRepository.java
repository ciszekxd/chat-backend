package com.anon.chat.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUserDto, Long> {

    @Query("SELECT COUNT(*) FROM ChatUserDto cu")
    Integer findAmountOfAll();

    @Query("SELECT cu.userHash FROM ChatUserDto cu")
    List<String> findAllUsers();

}
