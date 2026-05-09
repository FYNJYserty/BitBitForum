package com.bitbitforum.repository;

import com.bitbitforum.entity.Chatstatus;
import com.bitbitforum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatStatusRepository extends JpaRepository<Chatstatus, Long> {
    
    @Query("SELECT CASE WHEN COUNT(cs) > 0 THEN true ELSE false END FROM Chatstatus cs WHERE cs.chat.id = :chatId AND cs.usr.idUsr = :usrId")
    boolean existsByChatIdAndUsrId(@Param("chatId") Long chatId, @Param("usrId") Long usrId);

    @Query("SELECT u.username FROM User u JOIN Chatstatus cs ON u.idUsr = cs.usr.idUsr WHERE cs.id.chatId = :chatId")
    List<String> findAllUsersByChatId(@Param("chatId") Long chatId);

    @Query("SELECT cs.usr.idUsr FROM Chatstatus cs WHERE cs.chat.id = :chatId AND cs.status = \"admin\"")
    List<Long> findUsrIdByChatId(Long chatId);

    void deleteAllByChatId(Long chatId);
}
