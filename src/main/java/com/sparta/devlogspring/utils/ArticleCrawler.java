package com.sparta.devlogspring.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class ArticleCrawler {

    public static ChromeDriver getStarted() {
        ChromeOptions options = new ChromeOptions()
                .addArguments("--headless", "--no-sandbox", "--single-process", "--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    public static void finish(ChromeDriver chromeDriver) {
        chromeDriver.quit();
    }

    public static String extractHost(String urlString) {
        String result = "";
        try {
            URL url = new URL(urlString);
            String host = url.getHost();
            Pattern pattern = Pattern.compile("(?:blog\\.)*[a-z]+\\.[a-z]{2,4}$");
            Matcher match = pattern.matcher(host);
            if (match.find()) {
                result =  match.group();
            }
        } catch (MalformedURLException e) {
            result = e.getMessage();
        }
        return result;
    }

    public static ArrayList<String> crawlerRouter(String url) {
        if (Objects.equals(extractHost(url),"tistory.com")) {
            return sitemapCrawl(url);
        } else if (Objects.equals(extractHost(url),"github.io")) {
            return sitemapCrawl(url);
        } else if (Objects.equals(extractHost(url),"velog.io")) {
            return velocityCrawl(url);
        }
        return new ArrayList<>();
    }

    public static ArrayList<String> sitemapCrawl(String url) {
        ChromeDriver driver = getStarted();
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
        finish(driver);
        return arrays;
    }

    public static ArrayList<String> velocityCrawl(String url) {
        ChromeDriver driver = getStarted();
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
            resultArray.add(element.getAttribute("href"));
        }
        finish(driver);
        return resultArray;
    }
}
