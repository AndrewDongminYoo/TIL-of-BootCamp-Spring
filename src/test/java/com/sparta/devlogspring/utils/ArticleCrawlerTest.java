package com.sparta.devlogspring.utils;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ArticleCrawlerTest {
    ChromeDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--allow-remote-connections-from-ips=127.0.0.1");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("URL 도메인 추출하기")
    void extractHost() {
        String urlString = "https://velog.io/@hellonayeon/ioc-di";
        String result = "";
        try {
            URL url = new URL(urlString);
            String host = url.getHost();
            Pattern pattern = Pattern.compile("(?:blog\\.)*[a-z]+\\.[a-z]{2,4}$");
            Matcher match = pattern.matcher(host);
            if (match.find()) {
                result = match.group();
            }
        } catch (MalformedURLException e) {
            result = e.getMessage();
        }
        System.out.println(result);
    }

    @Test
    @DisplayName("사이트맵 통해 크롤링")
    void sitemapCrawl() throws InterruptedException {
        String url = "https://l0u0l.tistory.com/";
        driver.get(url + "sitemap");
        String source = driver.getPageSource();
        Matcher matcherNum = Pattern.compile(url + "\\d+").matcher(source);
        Matcher matcherEnt = Pattern.compile(url + "entry/" + "[%\\-\\w\\d]+").matcher(source);
        if (matcherNum.find()) {
            while (matcherNum.find()) {
                String match = matcherNum.group();
                System.out.println(match);
            }
        } else if (matcherEnt.find()){
            while (matcherEnt.find()) {
                String match = matcherEnt.group();
                System.out.println(match);
            }
        }
    }

    @Test
    @DisplayName("벨로그 크롤링")
    void velocityCrawl() {
        String url = "https://velog.io/@leejh3224/";
        driver.get(url);
        String scroll = "window.scrollTo(0, document.body.scrollHeight);";
        String height = "return document.body.scrollHeight;";
        Long lastHeight = (Long) ((JavascriptExecutor) driver).executeScript(height);
        while (true) {
            ((JavascriptExecutor) driver).executeScript(scroll);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Long newHeight = (Long) ((JavascriptExecutor) driver).executeScript(height);
            if (lastHeight.equals(newHeight)) break;
            lastHeight = newHeight;
        }
        ArrayList<String> resultArray = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(
                By.xpath("//*[@id='root']/div[2]/div[3]/div[4]/div[3]/div/div/a"));
        for (WebElement element : elementList) {
            String href = element.getAttribute("href");
                resultArray.add(href);
        }
        System.out.println(resultArray);
    }

    @Test
    void metaTagsCrawl() {
        String url = "https://l0u0l.tistory.com/entry/Spring-Bean";
        String name = "양찬홍";
        driver.get(url);
        String title = driver.findElement(By.cssSelector("meta[property='og:title']")).getAttribute("content");
        String author = driver.findElement(By.cssSelector("meta[property='og:article:author']")).getAttribute("content");
        String siteName = driver.findElement(By.cssSelector("meta[property='og:site_name']")).getAttribute("content");
        String registered = driver.findElement(By.cssSelector("meta[property='og:regDate']")).getAttribute("content");
        String image = driver.findElement(By.cssSelector("meta[property='og:image']")).getAttribute("content");
        String description = driver.findElement(By.cssSelector("meta[property='og:description']")).getAttribute("content");
        Document article = new Document();
        article.put("id", ObjectId.get().toString());
        article.put("name", name);
        article.put("title", title);
        article.put("author", author);
        article.put("siteName", siteName);
        article.put("url", url);
        article.put("description", description);
        article.put("image", image);
        System.out.println(article);
    }

    @Test
    void velogCrawler() {
        String url = "https://velog.io/@leejh3224/AWS-https%EB%A1%9C-%EC%A0%95%EC%A0%81-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8-%ED%98%B8%EC%8A%A4%ED%8C%85%ED%95%98%EA%B8%B0";
        String name = "leejh3224";
        driver.get(url);
        String title = driver.getTitle();
        String image = driver.findElement(By.cssSelector("meta[property='og:image']")).getAttribute("content");
        String description = driver.findElement(By.cssSelector("meta[property='og:description']")).getAttribute("content");
        String author = driver.findElement(By.cssSelector("span.username a")).getText();
        String siteName = driver.findElement(By.cssSelector("a.user-logo")).getText();
        String registered = driver.findElement(By.cssSelector("div.information > span:nth-child(3)")).getText();
        Document article = new Document();
        article.put("id", ObjectId.get().toString());
        article.put("name", name);
        article.put("title", title);
        article.put("author", author);
        article.put("siteName", siteName);
        article.put("url", url);
        article.put("description", description);
        article.put("image", image);
        System.out.println(article);
    }

    @Test
    void getLocalTime() {
        String dateString = "20211110012355";
        LocalDateTime timeOutput = null;
        Matcher yesterday = Pattern.compile("[약 ]*(\\d{1,2})일 전").matcher(dateString);
        Matcher hoursAgo = Pattern.compile("[약 ]*(\\d{1,2})시간 전").matcher(dateString);
        Matcher minutesAgo = Pattern.compile("[약 ]*(\\d{1,2})분 전").matcher(dateString);
        Matcher secondsAgo = Pattern.compile("[약 ]*(\\d{1,2})초 전").matcher(dateString);
        Matcher regex1 = Pattern.compile("(\\d{4})년 (\\d{1,2})월 (\\d{1,2})일").matcher(dateString);
        Matcher regex2 = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})").matcher(dateString);
        Matcher regex3 = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})\\+09:00").matcher(dateString);
        if (Objects.equals(dateString,"어제")) {
            timeOutput = LocalDateTime.now().minusDays(1);
        } else if (yesterday.find()) {
            int day = Integer.parseInt(yesterday.group(1));
            timeOutput = LocalDateTime.now().minusDays(day);
        } else if (hoursAgo.find()) {
            int hour = Integer.parseInt(hoursAgo.group(1));
            timeOutput = LocalDateTime.now().minusHours(hour);
        } else if (minutesAgo.find()) {
            int minute = Integer.parseInt(minutesAgo.group(1));
            timeOutput = LocalDateTime.now().minusMinutes(minute);
        } else if (secondsAgo.find()) {
            int seconds = Integer.parseInt(secondsAgo.group(1));
            timeOutput = LocalDateTime.now().minusSeconds(seconds);
        } else if (regex1.find()) {
            int year = Integer.parseInt(regex1.group(1));
            int month = Integer.parseInt(regex1.group(2));
            int day = Integer.parseInt(regex1.group(3));
            LocalDate date = LocalDate.of(year, month, day);
            LocalTime time = LocalTime.now();
            timeOutput = LocalDateTime.of(date, time);
        } else if (regex2.find()) {
            timeOutput = LocalDateTime.parse(dateString,
                    DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        } else if (regex3.find()) {
            timeOutput = LocalDateTime.parse(dateString,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        System.out.println(timeOutput);
    }
}