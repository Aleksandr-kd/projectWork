package org.data.utils;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Scope("prototype")
public class BaseWaitUtils {

    private final WebDriver driver;
    private final Duration defaultTimeout = Duration.ofSeconds(5);

    public BaseWaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, defaultTimeout);
    }

    /**
     * Ждёт, пока элемент станет видимым
     */
    public WebElement waitUntilVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Ждёт, пока элемент станет кликабельным
     */
    public WebElement waitUntilClickable(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Ждёт, пока текст в элементе станет равным заданному
     */
    public boolean waitUntilTextEquals(By locator, String expectedText) {
        return getWait().until(ExpectedConditions.textToBe(locator, expectedText));
    }

    /**
     * Ждёт, пока текст в элементе ИЗМЕНИТСЯ с начального значения
     */
    public String waitUntilTextChanges(By locator, String initialText) {
        return getWait().until(driver -> {
            String currentText = driver.findElement(locator).getText();
            return !currentText.equals(initialText) ? currentText : null;
        });
    }

    /**
     * Ждёт, пока элемент исчезнет со страницы (invisible)
     */
    public boolean waitUntilInvisible(By locator) {
        return getWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Ждёт появления любого ненулевого текста в элементе
     */
    public String waitUntilTextNotEmpty(By locator) {
        return getWait().until(driver -> {
            String text = driver.findElement(locator).getText();
            return !text.trim().isEmpty() ? text : null;
        });
    }
}