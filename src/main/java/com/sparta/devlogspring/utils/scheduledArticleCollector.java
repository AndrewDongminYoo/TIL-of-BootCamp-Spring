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
    private final ArticleCrawler articleCrawler;

    public void updateCount(String name) {


    }

    @Scheduled(cron = "0 0/3 * * * *")
    public void updateArticle() throws InterruptedException {

        List<Member> memberList = memberRepository.findAll();
        System.out.println(memberList);
        for (Member member : memberList) {
            TimeUnit.SECONDS.sleep(1);
            String target = member.getBlog();
            String name = member.getName();
            try {
                new URL(target);
                ArrayList<ArticleRequestDto> dtoList = articleCrawler.crawlerRouter(target, name);
                for (ArticleRequestDto dto : dtoList) {
                    String siteName = dto.getSiteName();
                    member.setSiteName(siteName);
                    articleRepository.save(new Article(dto));
                }
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
            Long count = articleRepository.countByName(name);
            member.setArticles(count);
        }
    }
}
