package tests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

public class AllureOnFailExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();

        try {
            Field driverField = testInstance.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            WebDriver driver = (WebDriver) driverField.get(testInstance);

            if (driver != null) {
                if (driver instanceof TakesScreenshot) {
                    byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Failure screenshot", new ByteArrayInputStream(png));
                }
                Allure.addAttachment("Page source", "text/html", driver.getPageSource(), ".html");
                Allure.addAttachment("Current URL", driver.getCurrentUrl());
            }
        } catch (Exception ignored) {
        }
    }
}
