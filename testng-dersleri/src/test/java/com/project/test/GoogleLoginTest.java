package com.project.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class GoogleLoginTest extends BaseTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private SoftAssert softAssert;

    @BeforeClass
    public void setUp() {
        // WebDriver ve diğer bileşenlerin kurulumu
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        softAssert = new SoftAssert();
    }

    @Test
    public void login() {
        driver.get("https://www.n11.com/");

        // Giriş yap butonuna tıkla
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div[5]/div/div/div/a[2]")));
        loginButton.click();

        // Daha sonra butonuna tıkla
        WebElement denyButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dn-slide-deny-btn")));
        denyButton.click();

        // Sayfayı aşağı kaydır
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");

        // Google ile Giriş butonuna tıkla
        WebElement googleLoginButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("google-login-btn")));
        googleLoginButton.click();

        // Yeni sekmeye geçiş yap
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Google e-posta ve şifre girişi
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
        emailField.sendKeys("yusufbasatogrul@hotmail.com"); // E-posta adresinizi girin
        emailField.sendKeys(Keys.ENTER);

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordField.sendKeys("Deneme"); // Şifrenizi girin
        passwordField.sendKeys(Keys.ENTER);

        // Giriş işlemi tamamlandığında, orijinal pencereye geri dön
        driver.switchTo().window(originalWindow);

        // Girişin başarılı olduğunu kontrol et
        WebElement succesLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div[5]/div[1]/a")));
        softAssert.assertTrue(succesLogin.isDisplayed());

        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        // Tarayıcıyı kapat
        if (driver != null) {
            driver.quit();
        }
    }
}
