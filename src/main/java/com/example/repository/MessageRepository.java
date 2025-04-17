package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.entity.Message;



public interface MessageRepository extends JpaRepository<Message, Integer> {

List<Message> findByPostedBy(Integer accountId);


@Modifying
@Transactional
@Query("DELETE FROM Message m WHERE m.messageId = :id")
int deleteByMessageId(@Param("id") int id);

}
