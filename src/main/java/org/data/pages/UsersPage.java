package org.data.pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UsersPage extends AbsBasePage {

    @Autowired
    public UsersPage(@Qualifier("testDriver") WebDriver driver) {
        super(driver, "/");
    }

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement buttonLogin;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement inputName;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Создать новый список']")
    private WebElement buttonCreateNewWishList;

    @FindBy(xpath = "//button[text()='Создать']")
    private WebElement buttonCreateWishList;

    @FindBy(xpath = "//button[text()='Удалить']")
    private WebElement buttonDeleteWishList;

    @FindBy(xpath = "//button[text()='Просмотр']")
    private WebElement buttonViewWishList;

    @FindBy(xpath = "//button[text()='Добавить подарок']")
    private WebElement buttonAddPresent;

    @FindBy(xpath = "//button[text()='Удалить список']")
    private WebElement buttonDeletePresent;

    @FindBy(xpath = "//label[text()='Название']/following-sibling::input")
    private WebElement namePresent;

    @FindBy(xpath = "//label[text()='Описание (необязательно)']/following-sibling::textarea")
    private WebElement descriptionPresent;

    @FindBy(xpath = "//label[text()='Ссылка на магазин (необязательно)']/following-sibling::input")
    private WebElement linkStorePresent;

    @FindBy(xpath = "//label[text()='Цена (необязательно)']/following-sibling::input")
    private WebElement pricePresent;

    @FindBy(xpath = "//label[text()='Ссылка на изображение (необязательно)']/following-sibling::input")
    private WebElement linkImagePresent;

    @FindBy(xpath = "//button[text()='Добавить']")
    private WebElement buttonAddForm;

    @FindBy(xpath = "//div[@class='g-4 row row-cols-lg-3 row-cols-md-2 row-cols-1']/*[last()]" +
            "//div[@class='card-title h5']")
    private WebElement lastNamePresent;

    @FindBy(xpath = "//div[@class='g-4 row row-cols-lg-3 row-cols-md-2 row-cols-1']/*[last()]" +
            "//p[@class='card-text']")
    private WebElement lastDescriptionPresent;

    @Step("Получение название последнего желания")
    public String getPageTextNameRegistrationPresent() {
        return lastNamePresent.getText();
    }

    @Step("Получение описание последнего желания")
    public String getPageTextDescriptionPresent() {
        return lastDescriptionPresent.getText();
    }

    @Step("Ввести имя пользователя: {nameRegistration}")
    public void inputName(String nameRegistration) {
        inputName.click();
        inputName.sendKeys(nameRegistration);
    }

    @Step("Ввести пароль пользователя")
    public void inputPassword(String passwordRegistration) {
        inputPassword.sendKeys(passwordRegistration);
    }

    @Step("Нажать кнопку Войти")
    public void clickButtonLogin() {
        buttonLogin.click();
    }

    @Step("Заполнение формы входа пользователя")
    public void authorization() {
        String nameUser = System.getProperty("login");
        String passwordUser = System.getProperty("password");
        inputName(nameUser);
        inputPassword(passwordUser);
        clickButtonLogin();
        waitPageLoad();
    }

    @Step("Нажать кнопку Создать новый список")
    public void clickCreateNewWishList() {
        waitAndClick(By.xpath("//button[text()='Создать новый список']"));
    }


    @Step("Нажать кнопку Создать")
    public void clickButtonCreate() {
        buttonCreateWishList.click();
    }

    @Step("Заполнить название")
    public void setNameNewWishList(String name) {
        namePresent.click();
        namePresent.sendKeys(name);
    }

    @Step("Заполнить описание")
    public void setDescriptionNewWishList(String description) {
        descriptionPresent.click();
        descriptionPresent.sendKeys(description);
    }

    @Step("Заполнить форму создания нового списка желаний")
    public void formCreateNewWishList(String name, String description) {
        setNameNewWishList(name);
        setDescriptionNewWishList(description);
    }

    @Step("Удаление желания")
    public boolean isDeletePresent() {
        buttonDeleteWishList.click();
        return true;
    }

    @Step("Просмотр добавленного подарка")
    public void viewWishList() {
        new Actions(driver)
                .moveToElement(driver.findElement(By.xpath("//button[text()='Просмотр']")))
                .click()
                .perform();
    }

    @Step("Удаление списка")
    public boolean isDeleteWishList() {
        buttonDeletePresent.click();
        return true;
    }

    @Step("Получение описание последнего желания")
    public String getNameWishList() {
        waitUtils.waitUntilTextChanges(By.xpath("//button[text()='Просмотр']"), "Загрузка...");
        return nameWishList.getText();
    }


}
