package com.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogoutTest extends BaseTest {
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    @Test
    void testLogout() {
        login(BASE_URL, USERNAME, PASSWORD);
        driver.findElement(org.openqa.selenium.By.id("react-burger-menu-btn")).click();
        driver.findElement(org.openqa.selenium.By.id("logout_sidebar_link")).click();
        assertEquals(BASE_URL, driver.getCurrentUrl());
        assertTrue(driver.findElement(org.openqa.selenium.By.id("login-button")).isDisplayed());
    }
}
