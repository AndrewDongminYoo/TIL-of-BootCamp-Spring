package com.sparta.devlogspring.service;

import com.sparta.devlogspring.model.ArticleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleJpaRepository articleJpaRepository;
}
