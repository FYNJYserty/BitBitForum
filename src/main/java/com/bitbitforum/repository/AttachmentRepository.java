package com.bitbitforum.repository;

import com.bitbitforum.entity.Attachment;
import com.bitbitforum.entity.Chatcomment;
import com.bitbitforum.entity.Disccomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findByDisccom(Disccomment disccom);
    List<Attachment> findByChatcom(Chatcomment chatcom);
}
