package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.PaymentBlockPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.extension.ExtendWith;



import static org.junit.jupiter.api.Assertions.*;

@Epic("MTS")
@Feature("Payment block")
@ExtendWith(AllureOnFailExtension.class)

@Story("UI smoke")
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
    @Severity(SeverityLevel.NORMAL)
    void shouldCheckTitle() {
        HomePage home = new HomePage(driver).open();
        Allure.step("Проверить, что title страницы не пустой", () -> {
            assertNotNull(driver.getTitle());
            assertFalse(driver.getTitle().isBlank());
        });

        Allure.step("Проверить, что заголовок блока оплаты не пустой", () -> {
            assertFalse(home.getPayBlockTitle().isBlank());
        });
    }

    @Test
    @DisplayName("2. Проверка логотипов платежных систем (в блоке на главной)")
    @Severity(SeverityLevel.NORMAL)
    void shouldCheckPaymentSystemLogosInBlock() {
        HomePage home = new HomePage(driver).open();
        Allure.step("Проверить, что отображаются логотипы платёжных систем", () -> {
            assertTrue(home.hasPaymentSystemLogos());
        });
    }

    @Test
    @DisplayName("3. Проверка работы ссылки 'Подробнее о сервисе'")
    @Severity(SeverityLevel.NORMAL)
    void shouldCheckMoreAboutServiceLink() {
        HomePage home = new HomePage(driver).open();

        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

        String currentHandle = driver.getWindowHandle();
        int windowsBefore = driver.getWindowHandles().size();

        Allure.step("Нажать ссылку «Подробнее о сервисе»", home::openMoreAboutService);

        Allure.step("Переключиться на новое окно (если открылось)", () -> {
            if (driver.getWindowHandles().size() > windowsBefore) {
                for (String h : driver.getWindowHandles()) {
                    if (!h.equals(currentHandle)) {
                        driver.switchTo().window(h);
                        break;
                    }
                }
            }
        });

        Allure.step("Проверить, что URL содержит нужную часть", () -> {
            assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
        });
    }


    @Test
    @DisplayName("4. Проверка плейсхолдеров во всех вариантах оплаты")
    @Severity(SeverityLevel.NORMAL)
    void shouldCheckAllEmptyFieldLabels() {
        PaymentBlockPage pay = new HomePage(driver).open().openPaymentBlock().openConnection();

        Allure.step("Проверить плейсхолдеры формы «Услуги связи»", () -> {
            assertEquals("Номер телефона", pay.getConnectionPhonePlaceholder());
            assertEquals("Сумма", pay.getConnectionSumPlaceholder());
            assertEquals("E-mail для отправки чека", pay.getConnectionEmailPlaceholder());
        });

        Allure.step("Проверить плейсхолдеры формы «Домашний интернет»", () -> {
            pay.openInternet();
            assertEquals("Номер абонента", pay.getInternetPhonePlaceholder());
            assertEquals("Сумма", pay.getInternetSumPlaceholder());
            assertEquals("E-mail для отправки чека", pay.getInternetEmailPlaceholder());
        });

        Allure.step("Проверить плейсхолдер формы «Рассрочка»", () -> {
            pay.openInstalment();
            assertEquals("Номер счета на 44", pay.getInstalmentScorePlaceholder());
        });

        Allure.step("Проверить плейсхолдер формы «Задолженность»", () -> {
            pay.openDebt();
            assertEquals("Номер счета на 2073", pay.getDebtScorePlaceholder());
        });
    }


    @Test
    @DisplayName("5.проверка оплаты услуг связи(сумма/телефон/поля/логотипы)")
    @Severity(SeverityLevel.CRITICAL)
    void shouldCheckConnectionPaymentFlow() {
        PaymentBlockPage pay = new HomePage(driver)
                .open()
                .openPaymentBlock()
                .openConnection()
                .fillConnection("297777777", "10", "test@test.com")
                .submitConnection();

        Allure.step("Проверить, что на кнопке оплаты отображается сумма", () -> {
            String btnText = pay.getWidgetPayButtonText();
            assertTrue(btnText.contains("10") || btnText.contains("10.00"),
                    "На кнопке должна быть сумма (10 / 10.00). Текст: " + btnText);
        });

        Allure.step("Проверить сумму в описании виджета", () -> {
            String amount = pay.getPaymentAmountTextFromWidget();
            assertTrue(amount.contains("10") || amount.contains("10.00"));
        });

        Allure.step("Проверить описание платежа (код 375 и телефон)", () -> {
            String descr = pay.getPaymentDescriptionTextFromWidget();
            assertTrue(descr.contains("375") && descr.contains("297777777"));
        });

        Allure.step("Проверить подписи полей карты", () -> {
            assertTrue(pay.widgetHasCardFieldLabels());
        });

        Allure.step("Проверить логотипы платёжных систем в виджете", () -> {
            assertTrue(pay.widgetHasPaymentSystemLogos());
        });
    }
}

