package com.project.test;

import com.project.test.BaseTest;
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

import java.security.Key;
import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void login(){
        driver.get("https://www.n11.com/");
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div[5]/div/div/div/a[2]")).click();

        WebElement username = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));

        username.sendKeys("yusufbasatogrul@hotmail.com");
        password.sendKeys("Deneme.1234"+ Keys.ENTER);
        WebElement succesLogin = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div[5]/div[1]/a"));
        softAssert.assertTrue(succesLogin.isDisplayed());

    }

}
