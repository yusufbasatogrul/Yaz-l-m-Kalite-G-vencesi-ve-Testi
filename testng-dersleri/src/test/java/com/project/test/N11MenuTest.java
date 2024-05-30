import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class N11MenuTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.n11.com");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testModaMenu() {
        WebElement tamamButonu = driver.findElement(By.id("myLocation-close-info"));
        tamamButonu.click();
        WebElement modaMenu = driver.findElement(By.xpath("//a[@title='Moda']"));
        modaMenu.click();


        WebElement modaSection = driver.findElement(By.xpath("//h1[contains(text(),'Moda')]"));
        assertTrue(modaSection.isDisplayed(), "Moda sayfası yüklenemedi.");
    }

    @Test
    public void testElektronikMenu() {
        WebElement tamamButonu = driver.findElement(By.id("myLocation-close-info"));
        tamamButonu.click();
        WebElement elektronikMenu = driver.findElement(By.xpath("//a[@title='Elektronik']"));
        elektronikMenu.click();


        WebElement elektronikSection = driver.findElement(By.xpath("//h1[contains(text(),'Elektronik')]"));
        assertTrue(elektronikSection.isDisplayed(), "Elektronik sayfası yüklenemedi.");
    }

    @Test
    public void testEvYasamMenu() {
        WebElement tamamButonu = driver.findElement(By.id("myLocation-close-info"));
        tamamButonu.click();
        WebElement evYasamMenu = driver.findElement(By.xpath("//a[@title='Ev & Yaşam']"));
        evYasamMenu.click();


        WebElement evYasamSection = driver.findElement(By.xpath("//h1[contains(text(),'Ev & Yaşam')]"));
        assertTrue(evYasamSection.isDisplayed(), "Ev & Yaşam sayfası yüklenemedi.");
    }

    @Test
    public void testAnneBebekMenu() {
        WebElement tamamButonu = driver.findElement(By.id("myLocation-close-info"));
        tamamButonu.click();
        WebElement anneBebekMenu = driver.findElement(By.xpath("//a[@title='Anne & Bebek']"));
        anneBebekMenu.click();


        WebElement anneBebekSection = driver.findElement(By.xpath("//h1[contains(text(),'Anne & Bebek')]"));
        assertTrue(anneBebekSection.isDisplayed(), "Anne & Bebek sayfası yüklenemedi.");
    }

    @Test
    public void testKozmetikMenu() {
        WebElement tamamButonu = driver.findElement(By.id("myLocation-close-info"));
        tamamButonu.click();
        WebElement kozmetikMenu = driver.findElement(By.xpath("//a[@title='Kozmetik & Kişisel Bakım']"));
        kozmetikMenu.click();


        WebElement kozmetikSection = driver.findElement(By.xpath("//h1[contains(text(),'Kozmetik & Kişisel Bakım')]"));
        assertTrue(kozmetikSection.isDisplayed(), "Kozmetik & Kişisel Bakım sayfası yüklenemedi.");
    }

    @Test
    public void testMucevherSaatMenu() {
        WebElement tamamButonu = driver.findElement(By.id("myLocation-close-info"));
        tamamButonu.click();
        WebElement mucevherSaatMenu = driver.findElement(By.xpath("//a[@title='Mücevher & Saat']"));
        mucevherSaatMenu.click();


        WebElement mucevherSaatSection = driver.findElement(By.xpath("//h1[contains(text(),'Mücevher & Saat')]"));
        assertTrue(mucevherSaatSection.isDisplayed(), "Mücevher & Saat sayfası yüklenemedi.");
    }

    @Test
    public void testSporOutdoorMenu() {
        WebElement tamamButonu = driver.findElement(By.id("myLocation-close-info"));
        tamamButonu.click();
        WebElement sporOutdoorMenu = driver.findElement(By.xpath("//a[@title='Spor & Outdoor']"));
        sporOutdoorMenu.click();


        WebElement sporOutdoorSection = driver.findElement(By.xpath("//h1[contains(text(),'Spor & Outdoor')]"));
        assertTrue(sporOutdoorSection.isDisplayed(), "Spor & Outdoor sayfası yüklenemedi.");
    }
}
