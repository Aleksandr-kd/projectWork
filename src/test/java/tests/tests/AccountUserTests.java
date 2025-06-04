package tests.tests;

import org.data.TestApplication;
import org.data.pages.AccountUserPage;
import org.data.pages.UsersPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tests.support.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = TestApplication.class)
public class AccountUserTests extends BaseTest {

    private AccountUserPage accountUserPage;

    @BeforeEach
    void initPage() {
        accountUserPage = getPage(AccountUserPage.class);
    }


    @Test
    @Tag("users")
    @DisplayName("Регистрации пользователя.")
    public void userRegistration() {
        String titleLogin = "Вход в систему";
        String titleRegistration = "Регистрация";
        String titleAccount = "Мои списки желаний";

        accountUserPage.open();


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

