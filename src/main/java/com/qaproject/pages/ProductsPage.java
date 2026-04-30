package com.qaproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;

    @FindBy(className = "title")
    private WebElement pageTitle;
    @FindBy(css=".inventory_item_name")
    private List<WebElement> allProducts;
    @FindBy(id="add-to-cart")
    private WebElement btnAddToCart;
    @FindBy(className = "shopping_cart_badge")
    private WebElement badgeCount;
    @FindBy(className = "shopping_cart_link")
    private WebElement btnShoppingCart;
    @FindBy(id = "remove")
    private WebElement removeBtn;
    @FindBy(className ="shopping-cart-badge")
    private WebElement cartBadge;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLoaded() {
        return pageTitle.isDisplayed();
    }

    public String getTitle() {
        return pageTitle.getText();
    }
    public void clickproduct(int index){
        allProducts.get(index).click();
    }
    public void clickAddtocart(){
        btnAddToCart.click();
    }
    public String getBadgecount(){
        try{
            return  badgeCount.getText();
        } catch(Exception e) {
            return "0";

        }

    }
    public void clickCart(){

        btnShoppingCart.click();
    }
    public void clickRemove() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOf(removeBtn));
        removeBtn.click();
    }
    public boolean isCartBadgeDisplayed() {
        try {
            return cartBadge.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}


