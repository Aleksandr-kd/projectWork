//package org.data.utils;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.List;
//
//    public class WaitUtils {
//
//        private static final long DEFAULT_TIMEOUT = 10;
//
//        // Ожидание загрузки страницы
//        public static void waitForPageLoad(WebDriver driver) {
//            waitForPageLoad(driver, DEFAULT_TIMEOUT);
//        }
//
//        public static void waitForPageLoad(WebDriver driver, long timeoutInSeconds) {
//            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
//                    .until(d -> ((JavascriptExecutor) d)
//                            .executeScript("return document.readyState").equals("complete"));
//        }
//
//        // Ожидание видимости элемента
//        public static WebElement waitForElementVisible(WebDriver driver, By locator) {
//            return waitForElementVisible(driver, locator, DEFAULT_TIMEOUT);
//        }
//
//        public static WebElement waitForElementVisible(WebDriver driver, By locator, long timeoutInSeconds) {
//            return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
//                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
//        }
//
//        // Ожидание кликабельности элемента
//        public static WebElement waitForElementClickable(WebDriver driver, By locator) {
//            return waitForElementClickable(driver, locator, DEFAULT_TIMEOUT);
//        }
//
//        public static WebElement waitForElementClickable(WebDriver driver, By locator, long timeoutInSeconds) {
//            return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
//                    .until(ExpectedConditions.elementToBeClickable(locator));
//        }
//
//        // Дополнительные полезные методы
//        public static void waitForTextPresent(WebDriver driver, By locator, String text) {
//            new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
//                    .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
//        }
//
//        public static void waitForElementsVisible(WebDriver driver, By locator) {
//            new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
//                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
//        }
//
//        public static void waitForInvisibility(WebDriver driver, By locator) {
//            new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
//                    .until(ExpectedConditions.invisibilityOfElementLocated(locator));
//        }
//    }
