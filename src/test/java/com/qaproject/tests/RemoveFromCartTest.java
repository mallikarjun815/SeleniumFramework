package com.qaproject.tests;

import com.qaproject.pages.*;
import com.qaproject.pages.CartPage;
import com.qaproject.utils.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;

@Listeners({AllureTestNg.class})
public class RemoveFromCartTest extends BaseTest {

    @Test
    public void removeItemTest() {

        // Step 1: Login
        LoginPage login = new LoginPage(driver);
        login.login(config.getUsername(), config.getPassword());

        // Step 2: Add product
        ProductsPage product = new ProductsPage(driver);
        product.clickproduct(0);
        product.clickAddtocart();


        // Step 3: Remove product
        product.clickRemove();
        driver.navigate().back();
        int badge=Integer.parseInt( product.getBadgecount());

        // Step 4: Verify cart badge NOT present
        Assert.assertEquals(badge,0, "Cart badge should not be visible");
    }
}
