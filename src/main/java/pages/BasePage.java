package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected WebElement $(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void safeClick(By locator) {
        dismissCookiesIfPresent();
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollIntoView(el);
        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            jsClick(el);
        }
    }

    protected void type(By locator, String text) {
        dismissCookiesIfPresent();
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        scrollIntoView(el);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            el.click();
        } catch (Exception ignored) {
        }
        try {
            el.clear();
        } catch (InvalidElementStateException ignored) {
        }
        el.sendKeys(text);
    }

    protected String attr(By locator, String name) {
        return $(locator).getAttribute(name);
    }

    protected String text(By locator) {
        return $(locator).getText();
    }

    protected void scrollIntoView(WebElement el) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        } catch (Exception ignored) {
        }
    }

    protected void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected void dismissCookiesIfPresent() {
        By cookie = By.cssSelector("div.cookie.show, .cookie.show, .cookie, #cookie, .cookies, .cookies__wrap");
        try {
            WebElement c = driver.findElement(cookie);
            if (c.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", c);
            }
        } catch (NoSuchElementException ignored) {
        }
    }

    protected String switchToNewTab() {
        String current = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String h : handles) {
            if (!h.equals(current)) {
                driver.switchTo().window(h);
                return current;
            }
        }
        return current;
    }
}
