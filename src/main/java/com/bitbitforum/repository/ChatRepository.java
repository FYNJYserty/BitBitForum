package com.bitbitforum.repository;

import com.bitbitforum.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    
    @Query("SELECT DISTINCT c FROM Chat c JOIN c.chatstatuses cs WHERE cs.usr.idUsr = :userId")
    List<Chat> findChatsByUserId(@Param("userId") Long userId);

    Chat findChatById(Long id);

}
