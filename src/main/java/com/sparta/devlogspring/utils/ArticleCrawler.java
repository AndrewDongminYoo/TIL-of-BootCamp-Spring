package com.sparta.devlogspring.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.regex.Matcher;
import java.util.regex.MatchResult;

import java.util.List;
import java.util.regex.Pattern;

public class ArticleCrawler {
    static ChromeOptions options = new ChromeOptions()
            .addArguments("--headless", "--no-sandbox", "--single-process", "--disable-dev-shm-usage");
    static ChromeDriver driver = new ChromeDriver(options);

    public static void crawl(String url) {
        driver.get(url + "sitemap");
        String source = driver.getPageSource();
        Matcher matcher = Pattern.compile(url + "[0-9]+").matcher(source);
        MatchResult urlList = matcher.toMatchResult();
        System.out.println(urlList);
    }

    public static void main(String[] args) {
        ArticleCrawler.crawl("https://cat-minzzi.tistory.com/");
    }
}
