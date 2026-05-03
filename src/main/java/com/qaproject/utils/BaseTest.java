package com.qaproject.utils;

import com.qaproject.config.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;
    protected ConfigReader config = new ConfigReader();

    @BeforeMethod
    public void setUp() {

        String browser = System.getProperty("browser", "chrome");
        System.out.println("Running on browser: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-translate");
            options.addArguments("--no-first-run");
            options.addArguments("--no-default-browser-check");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-features=PasswordLeakDetection");
            options.addArguments("--disable-features=SafeBrowsingEnhancedProtection");
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.default_content_setting_values.notifications", 2);
            options.setExperimentalOption("prefs", prefs);

            options.setExperimentalOption("excludeSwitches",
                    new String[]{"enable-automation"});

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();

        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get(config.getBaseUrl());
    }

    public void takeScreenshot(String testName) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String fileName = "screenshots/" + testName + "_" +
                LocalDateTime.now().toString().replace(":", "-") + ".png";

        File dest = new File(fileName);

        try {
            Files.copy(src.toPath(), dest.toPath());
            System.out.println("Screenshot saved: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenshot(result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }
}