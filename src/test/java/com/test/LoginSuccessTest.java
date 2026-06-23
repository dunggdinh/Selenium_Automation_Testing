package com.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginSuccessTest extends BaseTest {
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    @Test
    void testLoginSuccess() {
        login(BASE_URL, USERNAME, PASSWORD);
        assertTrue(driver.getCurrentUrl().endsWith("inventory.html"));
        assertEquals("Products", driver.findElement(org.openqa.selenium.By.className("title")).getText());
    }
}
