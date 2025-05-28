package tests.tests;

import org.data.TestApplication;
import org.data.pages.AccountUserPage;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import tests.support.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = TestApplication.class)
public class AccountUserTests  {


    @BeforeEach
    public void setUp() {
        accountUserPage.open();
    }

    @Autowired
    private AccountUserPage accountUserPage;

    @Test
    @Tag("account")
    @DisplayName("Регистрации пользователя.")
    public void userRegistration() {
        String titleLogin = "Вход в систему";
        String titleRegistration = "Регистрация";
        String titleAccount = "Мои списки желаний";

        String textRegistration = accountUserPage.getPageTextRegistration();
        assertThat(textRegistration).isEqualTo(titleRegistration);

        accountUserPage.registration();

        String textLogin = accountUserPage.getTextLogin();
        assertThat(textLogin).isEqualTo(titleLogin);

        accountUserPage.authorization();
        String textAccount = accountUserPage.getTextAccount();
        assertThat(textAccount).isEqualTo(titleAccount);
    }
}

