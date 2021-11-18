package com.sparta.devlogspring.utils;

import com.sparta.devlogspring.dto.ArticleRequestDto;
import com.sparta.devlogspring.model.Article;
import com.sparta.devlogspring.model.ArticleJpaRepository;
import com.sparta.devlogspring.model.Member;
import com.sparta.devlogspring.model.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ScheduledArticleCollector {

    private final ArticleJpaRepository articleRepository;
    private final MemberJpaRepository memberRepository;
    private final ArticleCrawler articleCrawler;

    @Transactional
    @Scheduled(cron = "0 0 9,21 * * *")
    public void updateArticle() throws InterruptedException {

        List<Member> memberList = memberRepository.findAll();
        System.out.println(memberList);
        for (Member member : memberList) {
            TimeUnit.SECONDS.sleep(1);
            String target = member.getBlog();
            String name = member.getName();
            if (target==null) continue;
            try {
                new URL(target);
                ArrayList<ArticleRequestDto> dtoList = articleCrawler.crawlerRouter(target, name);
                for (ArticleRequestDto dto : dtoList) {
                    String siteName = dto.getSiteName();
                    member.setSiteName(siteName);
                    memberRepository.save(member);
                    if (articleRepository.existsArticlesByUrl(dto.getUrl())) continue;
                    articleRepository.save(new Article(dto));
                }
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage()+" URL "+target);
            }
            Long count = articleRepository.countByName(name);
            member.setArticles(count);
            memberRepository.save(member);
        }
    }
}
