package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final String URL = "https://mts.by";

    private final By payBlockTitle = By.cssSelector("#pay-section h2, .pay__title, .pay-section__title");
    private final By paymentSystemLogos = By.cssSelector("#pay-section img, #pay-section .pay__partners img, #pay-section .partners img");
    private final By moreAboutServiceLink = By.cssSelector("#pay-section a[href*='service'], #pay-section a[href*='oplata'], #pay-section a[href*='pay'], #pay-section a");

    private final By paySection = By.cssSelector("#pay-section");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get(URL);
        wait.until(d -> d.findElement(paySection).isDisplayed());
        dismissCookiesIfPresent();
        return this;
    }

    public String getPayBlockTitle() {
        return text(payBlockTitle).trim();
    }

    public boolean hasPaymentSystemLogos() {
        return driver.findElements(paymentSystemLogos).size() > 0;
    }

    public HomePage openMoreAboutService() {
        safeClick(moreAboutServiceLink);
        return this;
    }

    public PaymentBlockPage openPaymentBlock() {
        return new PaymentBlockPage(driver);
    }
}
