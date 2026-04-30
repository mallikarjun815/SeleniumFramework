package com.qaproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    @FindBy(className = "title")
    private WebElement pageTitle;
    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;
    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemName;
    @FindBy(className = "shopping_cart_badge")
    WebElement CartBadge;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public boolean isPageDisplayed()
    {
        return pageTitle.isDisplayed() &&
                pageTitle.getText().equals("Your Cart");
    }
    public String getPageTitle() {
        return pageTitle.getText();
    }

    public int getCartItemCount() {
        return (int) cartItems.size();
    }

    public String getItemName(int index) {
        return itemName.get(index).getText();
    }
}

