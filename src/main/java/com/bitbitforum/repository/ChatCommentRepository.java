package com.bitbitforum.repository;

import com.bitbitforum.entity.Chatcomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatCommentRepository extends JpaRepository<Chatcomment, Long> {
    List<Chatcomment> findAllByChatId(Long chatId);

    void deleteAllByChatId(Long chatId);
}
