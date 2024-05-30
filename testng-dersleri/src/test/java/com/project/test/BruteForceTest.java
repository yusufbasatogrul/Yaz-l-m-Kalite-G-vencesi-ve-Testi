package com.project.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BruteForceTest {

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
    public void testBruteForceProtection() throws IOException {
        driver.get("https://www.n11.com/");
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div[5]/div/div/div/a[2]")).click();

        boolean captchaDisplayed = false;
        int attempt = 0;

        BufferedWriter writer = new BufferedWriter(new FileWriter("brute-force-test-results.csv"));
        writer.write("Tesebbüs,Sonuc\n");

        for (int i = 0; i < 10; i++) {
            WebElement username = driver.findElement(By.id("email"));
            WebElement password = driver.findElement(By.id("password"));

            username.clear();
            username.sendKeys("yusufbasatogrul@hotmail.com");

            password.clear();
            password.sendKeys("hahhahhsahahha" + i);
            password.sendKeys(Keys.ENTER);

            WebElement errorMessage = driver.findElement(By.xpath("//div[contains(@class, 'error') or contains(text(), 'hatalı')]"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Hatalı parola denemesine rağmen giriş yapılabiliyor!");
            writer.write((i + 1) + ",Ariza\n");

            try {
                WebElement captcha = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/div/div[1]"));
                if (captcha.isDisplayed()) {
                    captchaDisplayed = true;
                    attempt = i + 1; // 0'dan başladığı için 1 ekliyoruz
                    writer.write(attempt + ",CAPTCHA Displayed\n");
                    System.out.println("CAPTCHA " + attempt + ". yanlis giris denemesinden sonra goruntulendi.");
                    break;
                }
            } catch (NoSuchElementException e) {
                // CAPTCHA henüz görünmüyor, denemeye devam et
            }
        }

        Assert.assertTrue(captchaDisplayed, "CAPTCHA hiç görüntülenmedi!");
        writer.close();
    }
}
