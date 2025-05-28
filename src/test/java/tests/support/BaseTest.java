package tests.support;

import io.qameta.allure.Step;
import org.data.TestApplication;
import org.data.factory.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootTest(classes = {TestApplication.class, WebDriverManager.class})
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public abstract class BaseTest {

    @Autowired
    protected ApplicationContext context;

    protected WebDriver driver;


    @BeforeEach
    void setUp() {
        // Получаем экземпляр один раз
        driver = context.getBean("testDriver", WebDriver.class);

        // Регистрируем этот же экземпляр — но только если ещё не зарегистрирован
        var factory = ((ConfigurableApplicationContext) context).getBeanFactory();
        if (!factory.containsSingleton("testDriver")) {
            factory.registerSingleton("testDriver", driver);
        }
    }


    @Step("Закрытие драйвера")
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected <T> T getPage(Class<T> clazz) {
        return context.getBean(clazz);
    }
}