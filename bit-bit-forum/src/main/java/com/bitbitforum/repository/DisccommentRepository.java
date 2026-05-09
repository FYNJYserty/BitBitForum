package com.bitbitforum.repository;

import com.bitbitforum.entity.Disccomment;
import com.bitbitforum.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Supplier;

@Repository
public interface DisccommentRepository extends JpaRepository<Disccomment, Long> {
    List<Disccomment> findAllByDisc (Discussion discussion);
    void deleteByDiscId (Long discussionId);
}
