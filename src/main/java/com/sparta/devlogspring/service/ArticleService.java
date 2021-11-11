package com.sparta.devlogspring.service;

import com.sparta.devlogspring.dto.ArticleRequestDto;
import com.sparta.devlogspring.model.Article;
import com.sparta.devlogspring.model.ArticleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleJpaRepository articleJpaRepository;

//    @Transactional
//    public Long update(Long id, ArticleRequestDto requestDto) {
//        Article article = articleJpaRepository.findById(id).orElseThrow(
//                ()-> new NullPointerException("아이디가 존재하지 않습니다.")
//        );
//        article.update(requestDto);
//        return id;
//    }
}
