package com.sparta.devlogspring.model;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleJpaRepository extends JpaRepository<Article, String> {
    long countByName(String name);
    boolean existsArticlesByUrl(String href);
}
