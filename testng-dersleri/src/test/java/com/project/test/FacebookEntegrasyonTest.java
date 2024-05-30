package com.project.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.time.Duration;

public class FacebookEntegrasyonTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        // ChromeOptions ile tarayıcıyı başlatıyoruz.
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void facebookLogin() {
        driver.get("https://www.n11.com/");

        // Giriş yap butonuna tıklıyoruz.
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div[5]/div/div/div/a[2]")));
        loginButton.click();

        // Daha sonra butonuna tıklıyoruz.
        WebElement denyButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dn-slide-deny-btn")));
        denyButton.click();

        // Sayfayı aşağı kaydırıyoruz.
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");

        // Facebook ile Giriş butonuna tıklıyoruz.
        WebElement facebookLoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"contentWrapper\"]/div/div/div[2]/div[2]/div[2]/div")));
        facebookLoginButton.click();

        // Yeni sekmeye geçiş yapıyoruz.
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Facebook kullanıcı adı ve şifresini giriyoruz.
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email\"]")));
        emailInput.sendKeys("yusufbasatogrul@hotmail.com");

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")));
        passwordInput.sendKeys("Deneme.1234");

        WebElement loginBtn = driver.findElement(By.name("login"));
        loginBtn.click();

        // Giriş işlemi tamamlandığında, orijinal pencereye geri dönmsini sağlıyoruz.
        driver.switchTo().window(originalWindow);

        // Ana sayfaya geri dönüzyoruz.
        driver.get("https://www.n11.com/");

        WebElement unsuccessLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div[5]/div")));
        softAssert.assertFalse(unsuccessLogin.isDisplayed(), "n11 ile Facebook Girişi Başarısız");
    }

    @AfterClass
    public void tearDown() {
        // Tarayıcıyı kapatıyoruz.
        if (driver != null) {
            driver.quit();
        }
    }
}
