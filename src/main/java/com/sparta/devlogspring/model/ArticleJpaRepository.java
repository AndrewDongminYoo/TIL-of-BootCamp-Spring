package com.sparta.devlogspring.model;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleJpaRepository extends JpaRepository<Article, UUID> {
    long countByNameIs(String name);

}
