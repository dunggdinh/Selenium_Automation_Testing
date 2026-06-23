package com.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {
    protected static ChromeOptions options;
    protected WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void login(String baseUrl, String username, String password) {
        driver.get(baseUrl);
        driver.findElement(org.openqa.selenium.By.id("user-name")).sendKeys(username);
        driver.findElement(org.openqa.selenium.By.id("password")).sendKeys(password);
        driver.findElement(org.openqa.selenium.By.id("login-button")).click();
    }
}
