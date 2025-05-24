package org.data.pages;

import io.qameta.allure.Step;
import org.data.dto.User;
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
public class AccountUserPage extends AbsBasePage {

    @Autowired
    public AccountUserPage(@Qualifier("testDriver") WebDriver driver) {
        super(driver, "/register");
    }

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement buttonLogin;

    @FindBy(xpath = "//a[text()='Регистрация']")
    private WebElement registration;

    @FindBy(xpath = "//h2[text()='Регистрация']")
    private WebElement pageHeaderRegistration;

    @FindBy(xpath = "//input[@type='text']")
    private WebElement inputName;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonRegistration;

    @Step("Получение текста авторизации")
    public String getTextAccount() {
        return waitAndGetText(By.xpath("//h2[text()='Мои списки желаний']"));
    }

    @Step("Заполнение формы входа пользователя")
    public void registration() {
        User user = new User();
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        clickButtonRegistration();
        waitPageLoad();
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

    @Step("Получение текста страницы Вход в систему")
    public String getTextLogin() {
        return waitAndGetText(By.xpath("//h2[text()='Вход в систему']"));
    }

    @Step("Получение текста страницы Регистрации")
    public String getPageTextRegistration() {
        return pageHeaderRegistration.getText();
    }

    @Step("Проверка открытия страницы Регистрация")
    public void openRegistration() {
        registration.click();
    }

    @Step("Нажать кнопку зарегистрировать")
    public void clickButtonRegistration() {
        buttonRegistration.click();
    }

    @Step("Ввести имя пользователя: {nameRegistration}")
    public void inputName(String nameRegistration) {
        inputName.click();
        inputName.sendKeys(nameRegistration);
    }

    @Step("Ввести email пользователя")
    public void inputEmail(String emailRegistration) {
        inputEmail.sendKeys(emailRegistration);
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
    public void formAuthorization(String name, String password) {
        isElementVisible(By.xpath("//h2[text()='Вход в систему']"));
        inputName(name);
        inputPassword(password);
    }
}