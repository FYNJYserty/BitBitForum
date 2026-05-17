package com.bitbitforum.repository;

import com.bitbitforum.entity.Discstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscStatusRepository extends JpaRepository<Discstatus, Long> {
    void deleteByDiscId(Long discId);
}
