package tests;

import org.data.TestApplication;
import org.data.pages.AccountUserPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = TestApplication.class)
public class AccountUserTests extends BaseTest{


    @BeforeEach
    void setUp() {
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

        accountUserPage.registration();
        String textRegistration = accountUserPage.getPageTextRegistration();
        assertThat(textRegistration).isEqualTo(titleRegistration);

        String textLogin = accountUserPage.getTextLogin();
        assertThat(textLogin).isEqualTo(titleLogin);
    }

    @Test
    @Tag("account1")
    @DisplayName("Авторизация пользователя.")
    public void login() {
        String titleAccount = "Мои списки желаний";

        accountUserPage.authorization();
        String textAccount = accountUserPage.getTextAccount();
        assertThat(textAccount).isEqualTo(titleAccount);
    }
}
