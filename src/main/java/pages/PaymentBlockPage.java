// src/main/java/pages/PaymentBlockPage.java
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PaymentBlockPage extends BasePage {

    private final By paySelectHeader = By.cssSelector("#pay-section .select__header");
    private final By payDropdownList = By.cssSelector("#pay-section ul.select__list");
    private final By payDropdownItems = By.cssSelector("#pay-section ul.select__list li.select__item");

    private final By formConnection = By.cssSelector("form#pay-connection.pay-form.opened, form#pay-connection");
    private final By formInternet = By.cssSelector("form#pay-internet.pay-form.opened, form#pay-internet");
    private final By formInstalment = By.cssSelector("form#pay-instalment.pay-form.opened, form#pay-instalment");
    private final By formDebt = By.cssSelector("form#pay-arrears.pay-form.opened, form#pay-arrears");

    private final By connectionPhone = By.id("connection-phone");
    private final By connectionSum = By.id("connection-sum");
    private final By connectionEmail = By.id("connection-email");
    private final By connectionSubmit = By.cssSelector("form#pay-connection button[type='submit']");

    private final By internetPhone = By.id("internet-phone");
    private final By internetSum = By.id("internet-sum");
    private final By internetEmail = By.id("internet-email");

    private final By instalmentScore = By.id("score-instalment");

    private final By debtScore = By.id("score-arrears");

    private final By widgetIframe = By.cssSelector("iframe.payment-widget-iframe");
    private final By widgetAmount = By.cssSelector(".pay-description__cost span");
    private final By widgetDescriptionText = By.cssSelector(".pay-description__text span");

    private final By widgetLabelCardNumber = By.xpath("//label[normalize-space()='Номер карты']");
    private final By widgetLabelExpiry = By.xpath("//label[normalize-space()='Срок действия']");
    private final By widgetLabelCvc = By.xpath("//label[normalize-space()='CVC']");
    private final By widgetLabelHolder = By.xpath("//label[normalize-space()='Имя и фамилия на карте']");

    private final By widgetPaySystemsIcons = By.cssSelector("img[alt*='VISA'], img[alt*='Visa'], img[src*='visa'], img[alt*='Master'], img[src*='master'], img[alt*='МИР'], img[alt*='MIR'], img[src*='mir']");
    private final By widgetAnyLogosFallback = By.cssSelector(".icons-container img, .pay-systems img, .card-input__icons img, img");
    private final By widgetPayButton = By.xpath(
            "//button[contains(normalize-space(.),'Оплат') or contains(normalize-space(.),'Продолж')]"
    );


    public PaymentBlockPage(WebDriver driver) {
        super(driver);
    }

    private void openDropdown() {
        dismissCookiesIfPresent();
        wait.until(ExpectedConditions.elementToBeClickable(paySelectHeader));
        safeClick(paySelectHeader);
        wait.until(ExpectedConditions.presenceOfElementLocated(payDropdownList));
    }

    private void chooseFromDropdown(String text) {
        openDropdown();
        for (WebElement li : driver.findElements(payDropdownItems)) {
            if (li.getText().trim().equalsIgnoreCase(text.trim())) {
                scrollIntoView(li);
                try {
                    li.click();
                } catch (Exception e) {
                    jsClick(li);
                }
                return;
            }
        }
        throw new IllegalStateException("Option not found: " + text);
    }

    public PaymentBlockPage openConnection() {
        chooseFromDropdown("Услуги связи");
        wait.until(ExpectedConditions.presenceOfElementLocated(formConnection));
        return this;
    }

    public PaymentBlockPage openInternet() {
        chooseFromDropdown("Домашний интернет");
        wait.until(ExpectedConditions.presenceOfElementLocated(formInternet));
        return this;
    }

    public PaymentBlockPage openInstalment() {
        chooseFromDropdown("Рассрочка");
        wait.until(ExpectedConditions.presenceOfElementLocated(formInstalment));
        wait.until(ExpectedConditions.presenceOfElementLocated(instalmentScore));
        return this;
    }

    public PaymentBlockPage openDebt() {
        chooseFromDropdown("Задолженность");
        wait.until(ExpectedConditions.presenceOfElementLocated(formDebt));
        wait.until(ExpectedConditions.presenceOfElementLocated(debtScore));
        return this;
    }

    public String getConnectionPhonePlaceholder() {
        return attr(connectionPhone, "placeholder");
    }

    public String getConnectionSumPlaceholder() {
        return attr(connectionSum, "placeholder");
    }

    public String getConnectionEmailPlaceholder() {
        return attr(connectionEmail, "placeholder");
    }

    public String getInstalmentScorePlaceholder() {
        return attr(instalmentScore, "placeholder");
    }

    public String getInternetPhonePlaceholder() {
        return attr(internetPhone, "placeholder");
    }

    public String getInternetSumPlaceholder() {
        return attr(internetSum, "placeholder");
    }

    public String getInternetEmailPlaceholder() {
        return attr(internetEmail, "placeholder");
    }

    public String getDebtScorePlaceholder() {
        return attr(debtScore, "placeholder");
    }

    public String getWidgetPayButtonText() {
        switchToWidget();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(widgetPayButton)).getText().trim();
        } finally {
            driver.switchTo().defaultContent();
        }
    }


    public PaymentBlockPage fillConnection(String phoneWithout375, String amount, String email) {
        dismissCookiesIfPresent();
        type(connectionPhone, phoneWithout375);
        type(connectionSum, amount);
        type(connectionEmail, email);
        return this;
    }

    public PaymentBlockPage submitConnection() {
        dismissCookiesIfPresent();
        safeClick(connectionSubmit);
        return this;
    }

    public String getPaymentAmountTextFromWidget() {
        switchToWidget();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(widgetAmount)).getText().trim();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public String getPaymentDescriptionTextFromWidget() {
        switchToWidget();
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(widgetDescriptionText)).getText().trim();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public boolean widgetHasCardFieldLabels() {
        switchToWidget();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(widgetLabelCardNumber));
            wait.until(ExpectedConditions.visibilityOfElementLocated(widgetLabelExpiry));
            wait.until(ExpectedConditions.visibilityOfElementLocated(widgetLabelCvc));
            wait.until(ExpectedConditions.visibilityOfElementLocated(widgetLabelHolder));
            return true;
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public boolean widgetHasPaymentSystemLogos() {
        switchToWidget();
        try {
            List<WebElement> strict = driver.findElements(widgetPaySystemsIcons);
            if (strict.size() >= 2) return true;

            List<WebElement> any = driver.findElements(widgetAnyLogosFallback);
            return any.size() > 0;
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    private void switchToWidget() {
        wait.until(ExpectedConditions.presenceOfElementLocated(widgetIframe));
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(widgetIframe));
        driver.switchTo().frame(frame);
    }
}
