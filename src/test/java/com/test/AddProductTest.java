package com.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddProductTest extends BaseTest {
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    @Test
    void testAddProductToCart() {
        login(BASE_URL, USERNAME, PASSWORD);
        driver.findElement(org.openqa.selenium.By.id("add-to-cart-sauce-labs-backpack")).click();
        String badgeText = driver.findElement(org.openqa.selenium.By.className("shopping_cart_badge")).getText();
        assertEquals("1", badgeText);

        driver.findElement(org.openqa.selenium.By.className("shopping_cart_link")).click();
        assertTrue(driver.findElement(org.openqa.selenium.By.className("inventory_item_name")).getText().contains("Sauce Labs Backpack"));
    }
}
