package test;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.PaymentBlockPage;


import static org.junit.jupiter.api.Assertions.*;


public class MtsPaymentTests {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    @DisplayName("1. Проверка названия блока")
    void shouldCheckTitle() {
        HomePage home = new HomePage(driver).open();
        assertNotNull(driver.getTitle());
        assertFalse(driver.getTitle().isBlank());
        assertFalse(home.getPayBlockTitle().isBlank());
    }

    @Test
    @DisplayName("2. Проверка логотипов платежных систем (в блоке на главной)")
    void shouldCheckPaymentSystemLogosInBlock() {
        HomePage home = new HomePage(driver).open();
        assertTrue(home.hasPaymentSystemLogos());
    }

    @Test
    @DisplayName("3. Проверка работы ссылки 'Подробнее о сервисе'")
    void shouldCheckMoreAboutServiceLink() {
        HomePage home = new HomePage(driver).open();

        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

        String currentHandle = driver.getWindowHandle();
        int windowsBefore = driver.getWindowHandles().size();

        home.openMoreAboutService();

        if (driver.getWindowHandles().size() > windowsBefore) {
            for (String h : driver.getWindowHandles()) {
                if (!h.equals(currentHandle)) {
                    driver.switchTo().window(h);
                    break;
                }
            }
        }


        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));

    }


    @Test
    @DisplayName("4. Проверка плейсхолдеров во всех вариантах оплаты")
    void shouldCheckAllEmptyFieldLabels() {
        PaymentBlockPage pay = new HomePage(driver).open().openPaymentBlock().openConnection();
        pay.openConnection();
        assertEquals("Номер телефона", pay.getConnectionPhonePlaceholder());
        assertEquals("Сумма", pay.getConnectionSumPlaceholder());
        assertEquals("E-mail для отправки чека", pay.getConnectionEmailPlaceholder());

        pay.openInternet();
        assertEquals("Номер абонента", pay.getInternetPhonePlaceholder());
        assertEquals("Сумма", pay.getInternetSumPlaceholder());
        assertEquals("E-mail для отправки чека", pay.getInternetEmailPlaceholder());

        pay.openInstalment();
        assertEquals("Номер счета на 44", pay.getInstalmentScorePlaceholder());

        pay.openDebt();
        assertEquals("Номер счета на 2073", pay.getDebtScorePlaceholder()); // пример ожидаемого текста
    }

    @Test
    @DisplayName("5.проверка оплаты услуг связи(сумма/телефон/поля/логотипы)")
    void shouldCheckConnectionPaymentFlow() {
        PaymentBlockPage pay = new HomePage(driver)
                .open()
                .openPaymentBlock()
                .openConnection()
                .fillConnection("297777777", "10", "test@test.com")
                .submitConnection();

        String amount = pay.getPaymentAmountTextFromWidget();
        assertTrue(amount.contains("10") || amount.contains("10.00"));

        String descr = pay.getPaymentDescriptionTextFromWidget();
        assertTrue(descr.contains("375") && descr.contains("297777777"));

        assertTrue(pay.widgetHasCardFieldLabels());
        assertTrue(pay.widgetHasPaymentSystemLogos());
    }
}
