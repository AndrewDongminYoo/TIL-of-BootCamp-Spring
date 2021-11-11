package com.sparta.devlogspring.utils;

import com.sparta.devlogspring.dto.ArticleRequestDto;
import com.sparta.devlogspring.model.Article;
import com.sparta.devlogspring.model.ArticleJpaRepository;
import com.sparta.devlogspring.model.Member;
import com.sparta.devlogspring.model.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class scheduledArticleCollector {

    private final ArticleJpaRepository articleRepository;
    private final MemberJpaRepository memberRepository;

    @Scheduled(cron = "0 0 1,9,17 * * *")
    public void updateArticle() throws InterruptedException {
        System.out.println("블로그를 탐색합니다.");
        List<Member> memberList = memberRepository.findAll();
        for (Member member : memberList) {
            TimeUnit.SECONDS.sleep(1);
            String target = member.getBlog();
            try {
                new URL(target);
                ArrayList<ArticleRequestDto> dtoList = ArticleCrawler.crawlerRouter(target);
                for (ArticleRequestDto dto : dtoList) {
                    articleRepository.save(new Article(dto));
                }
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
