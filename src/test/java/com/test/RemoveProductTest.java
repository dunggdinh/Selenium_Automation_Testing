package com.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoveProductTest extends BaseTest {
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    @Test
    void testRemoveProductFromCart() {
        login(BASE_URL, USERNAME, PASSWORD);
        driver.findElement(org.openqa.selenium.By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(org.openqa.selenium.By.className("shopping_cart_link")).click();
        driver.findElement(org.openqa.selenium.By.id("remove-sauce-labs-backpack")).click();
        assertTrue(driver.findElements(org.openqa.selenium.By.className("shopping_cart_badge")).isEmpty());
    }
}
