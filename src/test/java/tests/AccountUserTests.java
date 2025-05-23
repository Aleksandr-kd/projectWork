package tests;

import org.data.TestApplication;
import org.data.dto.User;
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
        User user = new User();
        String titleLogin = "Вход в систему";
        String titleRegistration = "Регистрация";
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();

        String textRegistration = accountUserPage.getPageTextRegistration();
        assertThat(textRegistration).isEqualTo(titleRegistration);

        accountUserPage.formRegistration(name, email, password);
        accountUserPage.clickButtonRegistration();

        String textLogin = accountUserPage.getTextLogin();
        assertThat(textLogin).isEqualTo(titleLogin);
    }

    @Test
    @Tag("account")
    @DisplayName("Авторизация пользователя.")
    public void login() {
        User user = new User();
        String titleAccount = "Мои списки желаний";
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();

        accountUserPage.formRegistration(name, email, password);
        accountUserPage.clickButtonRegistration();
        accountUserPage.formAuthorization(name, password);
        accountUserPage.clickButtonLogin();

        String textAccount = accountUserPage.getTextAccount();
        assertThat(textAccount).isEqualTo(titleAccount);
    }
}
