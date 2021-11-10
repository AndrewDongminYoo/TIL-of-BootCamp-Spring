package com.sparta.devlogspring.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class ArticleCrawler {
    static ChromeOptions options = new ChromeOptions()
            .addArguments("--headless", "--no-sandbox", "--single-process", "--disable-dev-shm-usage");
    static ChromeDriver driver = new ChromeDriver(options);

    public static ArrayList<String> sitemapCrawl(String url) {
        driver.get(url + "sitemap");
        String source = driver.getPageSource();
        ArrayList<String> arrays = new ArrayList<>();
        if (Pattern.compile(url+"\\d+").matcher(source).matches()) {
            Matcher matcher = Pattern.compile(url + "\\d+").matcher(source);
            while (matcher.find()) arrays.add(matcher.group());
        } else {
            String pattern = url+"entry/"+"[%\\-\\w\\d]+";
            Matcher matcher = Pattern.compile(pattern).matcher(source);
            while (matcher.find()) arrays.add(matcher.group());
        }
        return arrays;
    }

    public static ArrayList<String> velocityCrawl(String url) {
        driver.get(url);
        JavascriptExecutor jsExecutor = driver;
        String scroll = "window.scrollTo(0, document.body.scrollHeight);";
        String height = "return document.body.scrollHeight;";
        Long lastHeight = (Long) jsExecutor.executeScript(height);
        while (true) {
            jsExecutor.executeScript(scroll);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Long newHeight = (Long) jsExecutor.executeScript(height);
            if (lastHeight.equals(newHeight)) break;
            lastHeight = newHeight;
        }
        ArrayList<String> resultArray = new ArrayList<>();
        List<WebElement> elementList = driver.findElements(
                By.xpath("//*[@id='root']/div[2]/div[3]/div[4]/div[3]/div/div/a"));
        for (WebElement element : elementList) {
            resultArray.add(element.getAttribute("href"));
        }
        return resultArray;
    }

    public static void main(String[] args) {
        ArrayList<String> urlList = ArticleCrawler.sitemapCrawl("https://cat-minzzi.tistory.com/");
        ArrayList<String> urlList2 = ArticleCrawler.velocityCrawl("https://velog.io/@yu_jep/");
        System.out.println(urlList2);
    }
}
