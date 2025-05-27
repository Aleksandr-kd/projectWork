package tests;

import org.data.pages.AccountUserPage;
import org.data.pages.UsersPage;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseTest {

//    @Autowired
//    private AccountUserPage accountUserPage;
//
//    @Autowired
//    private UsersPage usersPage;
//
//    void setUpAccountUserPage() {
//        accountUserPage.open();
//    }
//
//    void setUpUsersPage() {
//        usersPage.open();
//    }

    protected WebDriver driver;

    @Autowired
    protected BaseWaitUtils waitUtils;


    @BeforeEach
    void setUpDriver() {
        driver = driverFactory.createDriver();
    }

    @AfterEach
    void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Получает PageObject с внедрением текущего драйвера
     */
    protected <T> T getPage(Class<T> pageClass) {
        return context.getBean(pageClass, driver);
    }
}