package com.mikemason.novel_outliner.data.repositories;

import com.mikemason.novel_outliner.data.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
