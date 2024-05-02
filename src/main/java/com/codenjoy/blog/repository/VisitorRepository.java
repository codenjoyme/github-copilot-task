package com.codenjoy.blog.repository;

import com.codenjoy.blog.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visit, Long> {

    List<Visit> findAll();

    Optional<Visit> findByFileName(String fileName);
}
