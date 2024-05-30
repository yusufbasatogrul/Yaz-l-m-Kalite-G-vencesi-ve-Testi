package com.project.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class mailEntegrasyonTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        // ChromeOptions ile tarayıcıyı başlatın
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        softAssert = new SoftAssert();
    }

    @Test
    public void n11PasswordResetTest() {
        driver.get("https://www.n11.com/");

        // Giriş yap butonuna tıkla
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div[5]/div/div/div/a[2]")));
        loginButton.click();

        // Şifremi unuttum seçeneğine tıkla
        WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Şifremi Unuttum')]")));
        forgotPasswordLink.click();

        // Daha sonra butonuna tıkla
        WebElement denyButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dn-slide-deny-btn")));
        denyButton.click();

        // E-posta adresini gir
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("forgottenUserEmail")));
        emailInput.sendKeys("yusufbasatogrul@hotmail.com");

        // Devam et butonuna tıkla
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("forgotPasswordButton")));
        continueButton.click();

        WebElement succesProcces = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"forgotPasswordLightBox\"]/div/div/div")));
        softAssert.assertTrue(succesProcces.isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        // Tarayıcıyı kapat
        if (driver != null) {
            driver.quit();
        }
    }
}
