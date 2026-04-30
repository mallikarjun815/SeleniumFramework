package com.qaproject.tests;

import com.qaproject.pages.*;
import com.qaproject.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutTest extends BaseTest {

    @Test
    public void completeCheckoutTest() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // Step 1: Login
        LoginPage login = new LoginPage(driver);
        login.login(config.getUsername(), config.getPassword());

        // Step 2: Add product
        ProductsPage product = new ProductsPage(driver);
        product.clickproduct(0);
        product.clickAddtocart();
        product.clickCart();

        // Step 3: Checkout
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clickCheckout();
        checkout.fillDetails("John", "Doe", "500001");
        checkout.clickContinue();
        checkout.clickFinish();

        // Step 4: Verify success
        Assert.assertEquals(
                checkout.getSuccessMessage(),
                "Thank you for your order!"
        );
    }
}
