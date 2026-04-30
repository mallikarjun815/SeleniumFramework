package com.qaproject.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    WebDriver driver;

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(id = "continue")
    WebElement continueBtn;

    @FindBy(id = "finish")
    WebElement finishBtn;

    @FindBy(className = "complete-header")
    WebElement successMsg;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCheckout() {
        checkoutBtn.click();
    }

    public void fillDetails(String f, String l, String p) {
        firstName.sendKeys(f);
        lastName.sendKeys(l);
        postalCode.sendKeys(p);
    }

    public void clickContinue() {
        continueBtn.click();
    }

    public void clickFinish() {
        finishBtn.click();
    }

    public String getSuccessMessage() {
        return successMsg.getText();
    }
}
