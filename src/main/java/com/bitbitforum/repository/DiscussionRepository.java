package com.bitbitforum.repository;

import com.bitbitforum.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    List<Discussion> findByAuthorIdUsr(Long authorIdUsr);
}
