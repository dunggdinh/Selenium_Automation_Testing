package com.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckoutTest extends BaseTest {
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String USERNAME = "standard_user";
    private static final String PASSWORD = "secret_sauce";

    @Test
    void testCheckoutProcess() {
        login(BASE_URL, USERNAME, PASSWORD);
        driver.findElement(org.openqa.selenium.By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(org.openqa.selenium.By.className("shopping_cart_link")).click();

        driver.findElement(org.openqa.selenium.By.id("checkout")).click();
        driver.findElement(org.openqa.selenium.By.id("first-name")).sendKeys("Nguyen");
        driver.findElement(org.openqa.selenium.By.id("last-name")).sendKeys("Dung");
        driver.findElement(org.openqa.selenium.By.id("postal-code")).sendKeys("70000");
        driver.findElement(org.openqa.selenium.By.id("continue")).click();

        driver.findElement(org.openqa.selenium.By.id("finish")).click();
        String complete = driver.findElement(org.openqa.selenium.By.className("complete-header")).getText();
        assertEquals("THANK YOU FOR YOUR ORDER", complete);
    }
}
