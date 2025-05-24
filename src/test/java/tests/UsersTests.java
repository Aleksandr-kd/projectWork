package tests;

import io.qameta.allure.Step;
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
    public void userRegistration() {

        String name = System.getProperty("login");
        String password = System.getProperty("password");

        WishList wishList = new WishList();
        String nameProduct = wishList.getProductName();
        String description = wishList.getDescription();

        usersPage.formAuthorization(name, password);
        usersPage.clickButtonLogin();
        usersPage.clickCreateNewWishList();
        usersPage.formCreateNewWishList(nameProduct, description);
        usersPage.clickButtonCreate();

        String nameCheck = usersPage.getPageTextNameRegistrationPresent();

        assertThat(nameCheck)
                .as("Элемент с названием %s не найден", nameProduct)
                .isEqualTo(nameProduct);

        String descriptionCheck = usersPage.getPageTextDescriptionPresent();
        assertThat(description).isEqualTo(descriptionCheck);

        assertThat(description)
                .as("Элемент с описанием %s не найден", descriptionCheck)
                .isEqualTo(descriptionCheck);

        Boolean deletePresent = usersPage.isDeletePresent();
        assertThat(deletePresent).as("Проверка удаления элемента").isTrue();
    }
}