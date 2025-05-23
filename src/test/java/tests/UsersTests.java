package tests;

import org.data.TestApplication;
import org.data.dto.User;
import org.data.dto.WishList;
import org.data.pages.UsersPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = TestApplication.class)
public class UsersTests extends BaseTest{

    @Autowired
    private UsersPage usersPage;

    @BeforeEach
    void setUp() {
        usersPage.open();
    }

    @Test
    @Tag("users")
    @DisplayName("Создание списка желаний.")
    public void userRegistration() throws InterruptedException {
        User user = new User();
        WishList wishList = new WishList();

        String name = System.getProperty("login");
        String password = System.getProperty("password");

        String nameProduct = wishList.getProductName();
        String description = wishList.getDescription();

        usersPage.formAuthorization(name, password);
        usersPage.clickButtonLogin();
        usersPage.clickCreateNewWishList();
        usersPage.formCreateNewWishList(nameProduct, description);
        usersPage.clickButtonCreate();

//        assertThat.

        Thread.sleep(2000);


    }
}