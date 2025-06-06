package org.data.pages;

import io.qameta.allure.Step;
import org.data.manager.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public abstract class AbsBasePage {

    protected WebDriver driver = null;
    private String path = "";
    private final String baseUrl = System.getProperty("base.url");
    protected final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
    protected final Duration POLLING_INTERVAL = Duration.ofMillis(500);

    public AbsBasePage(DriverManager driverManager, String path) {
        this.driver = driverManager.getDriver();
        this.path = path;
        PageFactory.initElements(driver, this);
    }

    @Step("Открытие страницы")
    public void open() {
        driver.get(baseUrl + path);
    }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }


    /**
     * Ждёт, пока текст в элементе ИЗМЕНИТСЯ с начального значения
     */
    public void waitUntilTextChanges(By locator, String initialText) {
        getWait().until(driver -> {
            String currentText = driver.findElement(locator).getText();
            return !currentText.equals(initialText) ? currentText : null;
        });
    }
    // Основные методы ожидания
//        PageFactory.initElements(driver, this);

    protected WebElement waitForElement(By locator) {
        return getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElement(By locator, Duration timeout) {
        return getFluentWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> waitForAllElements(By locator) {
        return getFluentWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        return getFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected Boolean waitForElementToDisappear(By locator) {
        return getFluentWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected Boolean waitForTextToBePresentInElement(By locator, String text) {
        return getFluentWait().until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    //Alert alert = wait.until(ExpectedConditions.alertIsPresent());

    @Step("Ожидание загрузки страницы")
    protected void waitPageLoad() {
        getFluentWait().until(d ->
                ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    @Step("Ожидание загрузки AJAX (jQuery)")
    protected void waitForJQueryLoad() {
        getFluentWait().until(d ->
                (Boolean) ((JavascriptExecutor) d).executeScript(
                        "return (typeof jQuery != 'undefined') ? jQuery.active == 0 : true"));
    }

    // Настройка FluentWait
    private FluentWait<WebDriver> getFluentWait() {
        return getFluentWait(DEFAULT_TIMEOUT);
    }

    private FluentWait<WebDriver> getFluentWait(Duration timeout) {
        return new FluentWait<>(driver)
                .withTimeout(timeout)
                .pollingEvery(POLLING_INTERVAL)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    // Дополнительные полезные методы

    @Step("Ожидание и клик по элементу")
    protected void waitAndClick(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    @Step("Ожидание и ввод текста")
    protected void waitAndSendKeys(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    @Step("Ожидание и получение текста")
    protected String waitAndGetText(By locator) {
        return waitForElement(locator).getText();
    }

    @Step("Проверка видимости элемента")
    protected void isElementVisible(By locator) {
        try {
            waitForElement(locator, Duration.ofSeconds(10)).isDisplayed();
        } catch (TimeoutException ignored) {
        }
    }
    // Ожидание появления alert
    public Alert waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // Заполнение и подтверждение prompt
    public void fillAndAcceptPrompt(String text) {
        Alert alert = waitForAlert();
        alert.sendKeys(text);
        alert.accept();
    }

    // Простое подтверждение alert
    public void acceptAlert() {
        waitForAlert().accept();
    }

    public void waitPageLoad(int timeoutInSeconds) {
        getFluentWait(Duration.ofDays(timeoutInSeconds))
                .until(d -> ((JavascriptExecutor) d)
                        .executeScript("return document.readyState")
                        .equals("complete"));
    }
}