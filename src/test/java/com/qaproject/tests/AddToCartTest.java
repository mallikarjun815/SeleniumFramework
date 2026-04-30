package com.qaproject.tests;

import com.qaproject.pages.CartPage;
import com.qaproject.pages.LoginPage;
import com.qaproject.pages.ProductsPage;
import com.qaproject.utils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartTest  extends BaseTest {
    @Test
    public void addFirstProductToCartTest(){
        // step login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getUsername(), config.getPassword());


        try {
            WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement okButton = popupWait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("cr-button[class*='action-button']")
                    )
            );
            okButton.click();
            System.out.println("Password popup dismissed!");
        } catch (Exception e) {
            System.out.println("No password popup, continuing...");
        }


        // step 2 verify product page is loaded or not
        ProductsPage productsPage= new ProductsPage(driver);
        Assert.assertTrue(productsPage.isLoaded(),
                "Products page should be loaded after login");
        // step 3 add first product to the cart
        productsPage.clickproduct(0);
        productsPage.clickAddtocart();
        // verify cart badge shows 1
        int  badge = Integer.parseInt( productsPage.getBadgecount());
        System.out.println("Badge: " + badge);
        Assert.assertEquals(badge, 1,
                "Badge should be 1 but was: " + badge);

        // click cart icon
        productsPage.clickCart();

        //verify cart page is loaded or not
        CartPage cartPage= new CartPage(driver);
        Assert.assertTrue(cartPage.isPageDisplayed(),"Cart page should be loaded");


        Assert.assertTrue(cartPage.getCartItemCount() == 1,"Cart should have 1 item");
        Assert.assertEquals(
                cartPage.getItemName(0),
                "Sauce Labs Backpack",
                "First item should be Sauce Labs Backpack"
        );
        System.out.println("✅ Item added: " +
                cartPage.getItemName(0));


    }


}

