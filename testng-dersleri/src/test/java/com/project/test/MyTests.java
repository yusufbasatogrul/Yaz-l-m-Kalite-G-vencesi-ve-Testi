package com.project.test;



import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.security.Key;
import java.time.Duration;

public class MyTests extends BaseTest{

    @Test(priority = 1)
    public void test01(){
        driver.get("https://www.n11.com/");
        driver.findElement(By.id("searchData")).sendKeys("Telefon"+ Keys.ENTER);
        WebElement resultWe = driver.findElement(By.xpath("//*[@id=\"contentListing\"]/div/div[2]/div[2]/section/div[1]/div[1]/div[1]/div"));
        Assert.assertTrue(resultWe.isDisplayed());
    }
    @Test(priority = 2)
    public void test02(){
        driver.findElement(By.xpath("//*[@id=\"dengage-push-perm-slide\"]/div/div[2]/div[2]/button[1]")).click();
        driver.findElement(By.xpath("//div[@data-position=\"3\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"unfDetailForm\"]/div[2]/div[2]/div[1]/button")).click();
        String count = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div[4]/a/span")).getText();
        Assert.assertFalse(count.equals("0"));

    }
}
