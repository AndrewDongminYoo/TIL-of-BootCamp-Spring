package com.sparta.devlogspring.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Chrome {

    public final ChromeDriver driver;
    public final ChromeOptions options;

    public Chrome(ChromeDriver driver, ChromeOptions options) {
        this.driver = (ChromeDriver) driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.options = options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage", "--allow-remote-connections-from-ips=127.0.0.1");
    }
}
