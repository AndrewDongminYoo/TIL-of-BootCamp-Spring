package com.sparta.devlogspring.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ChromeWebDriver extends ChromeDriver {

    public ChromeWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless",
                "--no-sandbox",
                "--disable-extensions",
                "--disable-gpu",
                "--window-size=1920,1200",
                "--ignore-certificate-errors",
                "--whitelisted-ips=''",
                "--disable-dev-shm-usage");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
