package com.qaproject.tests;
import com.qaproject.pages.LoginPage;
import com.qaproject.pages.ProductsPage;
import com.qaproject.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;
    @BeforeMethod
    public void init() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest() {
        loginPage.login(config.getUsername(), config.getPassword());

        ProductsPage products = new ProductsPage(driver);
        Assert.assertTrue(products.isLoaded(),
                "Products page should load after login");
        Assert.assertEquals(products.getTitle(), "Products");
    }

    @Test
    public void invalidLoginTest() {
        loginPage.login("wrong_user", "wrong_pass");

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username and password"),
                "Error message should appear"
        );
    }

    @Test(dataProvider = "loginData")
    public void dataDriverLoginTest(String user,
                                    String pass, boolean shouldPass) {
        loginPage.login(user, pass);

        if (shouldPass) {
            ProductsPage p = new ProductsPage(driver);
            Assert.assertTrue(p.isLoaded());
        } else {
            Assert.assertFalse(
                    loginPage.getErrorMessage().isEmpty());
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"standard_user",   "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"wrong_user",      "wrong_pass",   false}
        };
    }
}

