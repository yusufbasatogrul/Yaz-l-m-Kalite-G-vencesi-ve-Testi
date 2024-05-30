package com.project.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SQLInjectionTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSQLInjection() {
        driver.get("https://www.n11.com/");
        WebElement searchBox = driver.findElement(By.id("searchData"));
        searchBox.sendKeys("' OR 1=1 -- ");
        searchBox.sendKeys(Keys.ENTER);

        WebElement resultText = driver.findElement(By.className("resultText"));
        String text = resultText.getText();
        Assert.assertFalse(text.contains("SQL syntax"), "SQL Injection açığı mevcut!");
    }
}
