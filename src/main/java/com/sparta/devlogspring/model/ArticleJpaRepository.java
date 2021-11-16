package com.sparta.devlogspring.model;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleJpaRepository extends JpaRepository<Article, String> {
    long countByName(String name);
    boolean existsArticlesByUrl(String href);
    List<Article> findArticlesByDescriptionContainsOrTitleContains(String query1, String query2);
}
