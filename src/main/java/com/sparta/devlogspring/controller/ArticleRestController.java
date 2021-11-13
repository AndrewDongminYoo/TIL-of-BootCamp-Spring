package com.sparta.devlogspring.controller;

import com.sparta.devlogspring.model.Article;
import com.sparta.devlogspring.model.ArticleJpaRepository;
import com.sparta.devlogspring.model.Member;
import com.sparta.devlogspring.model.MemberJpaRepository;
import com.sparta.devlogspring.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleJpaRepository articleJpaRepository;
    private final ArticleService articleService;
    private final MemberJpaRepository memberJpaRepository;

    @GetMapping("/api/list")
    public List<Article> readArticle() {
        return articleJpaRepository
                .findAll()
                .stream()
                .sorted(Comparator
                        .comparing(Article::getRegistered)
                        .reversed())
                .limit(50)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/rank")
    public List<Member> rankerBlog() {
        return memberJpaRepository
                .findAll()
                .stream()
                .filter(Member::hasBlog)
                .filter(n->n.getArticles()>0)
                .sorted(Comparator
                        .comparing(Member::getArticles)
                        .reversed())
                .collect(Collectors.toList());
    }
    @GetMapping("/api/unrank")
    public List<Member> unRankedBlog() {
        return memberJpaRepository
                .findAll()
                .stream()
                .filter(Member::hasBlog)
                .filter(n->n.getArticles()==0)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/none")
    public List<Member> nonCollected() {
        return memberJpaRepository
                .findAll()
                .stream()
                .filter(member->member.getBlog().isEmpty())
                .collect(Collectors.toList());
    }
}

