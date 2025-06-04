package tests.support;

import io.qameta.allure.Step;
import org.data.TestApplication;
import org.data.manager.DriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = TestApplication.class)
public abstract class BaseTest {

    @Autowired
    private DriverManager driverManager;

    @Autowired
    private ApplicationContext context;

    @BeforeEach
    void setUp() {
        WebDriver driver = context.getBean("testDriver", WebDriver.class);
        driverManager.setDriver(driver);
    }

    @Step("Закрытие драйвера")
    @AfterEach
    public void tearDown() {
            driverManager.removeDriver();
    }

    protected <T> T getPage(Class<T> clazz) {
        return context.getAutowireCapableBeanFactory().createBean(clazz);
    }

}