import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.mts.by/");
    }

    private void handleCookies() {
        try {
            WebDriverWait quickWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement accept = quickWait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-accept")));
            accept.click();
        } catch (Exception e) {
            try {
                driver.findElement(By.xpath("//button[contains(text(),'Принять')]")).click();
            } catch (Exception ex) {
            }
        }
    }

    @Test
    @DisplayName("1. Проверка названия блока")
    void testBlockTitle() {
        handleCookies();
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='pay']//h2")));
        assertEquals("Онлайн пополнение без комиссии", title.getText().replace("\n", " "));
    }

    @Test
    @DisplayName("2. Проверка логотипов платежных систем")
    void testPaymentLogos() {
        handleCookies();
        WebElement partnersBlock = driver.findElement(By.className("pay__partners"));
        List<WebElement> logos = partnersBlock.findElements(By.tagName("img"));
        assertTrue(logos.size() > 0);
    }

    @Test
    @DisplayName("3. Проверка ссылки Подробнее о сервисе")
    void testMoreInfoLink() {
        handleCookies();
        WebElement detailsLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        detailsLink.click();
        assertTrue(driver.getCurrentUrl().contains("help"));
    }

    @Test
    @DisplayName("4. Проверка формы оплаты и кнопки Продолжить")
    void testPaymentForm() {
        handleCookies();
        WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("connection-phone")));
        phoneInput.sendKeys("297777777");

        WebElement sumInput = driver.findElement(By.id("connection-sum"));
        sumInput.sendKeys("10");

        WebElement continueBtn = driver.findElement(By.xpath("//form[@id='pay-connection']//button[contains(text(),'Продолжить')]"));
        continueBtn.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bpay-browser-frame, .bpay-title, iframe, [id*='payment']")));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}