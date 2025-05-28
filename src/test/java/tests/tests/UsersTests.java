package tests.tests;

import org.data.TestApplication;
import org.data.dto.WishList;
import org.data.pages.UsersPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tests.support.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = TestApplication.class)
public class UsersTests extends BaseTest {

    private UsersPage usersPage;

    @BeforeEach
    void initPage() {
        usersPage = getPage(UsersPage.class);
//        wishList = getPage(WishList.class);
    }

    @Test
    @Tag("users")
    @DisplayName("Управление пользователем списка желаний.")
    public void userPresentWishList() {
        WishList wishList = new WishList();
        String nameProduct = wishList.getProductName();
        String description = wishList.getDescription();

        usersPage.open();
        usersPage.authorization();
        usersPage.clickCreateNewWishList();
        usersPage.formCreateNewWishList(nameProduct, description);
        usersPage.clickButtonCreate();

        String nameCheck = usersPage.getPageTextNameRegistrationPresent();
        assertThat(nameCheck)
                .as("Элемент с названием %s не найден", nameProduct)
                .isEqualTo(nameProduct);

        String descriptionCheck = usersPage.getPageTextDescriptionPresent();
        assertThat(description)
                .as("Элемент с описанием %s не найден", descriptionCheck)
                .isEqualTo(descriptionCheck);

        Boolean isDeletePresent = usersPage.isDeletePresent();
        assertThat(isDeletePresent).as("Проверка удаления элемента").isTrue();
    }

    @Test
    @Tag("users1")
    @DisplayName("Управление пользовательского подарка. Поиск и удаление подарка.")
    public void userPresentView() {

        WishList wishList = new WishList();
        String nameProduct = wishList.getProductName();
        String description = wishList.getDescription();

        usersPage.open();
        usersPage.authorization();
        usersPage.clickCreateNewWishList();
        usersPage.formCreateNewWishList(nameProduct, description);
        usersPage.clickButtonCreate();

        usersPage.viewWishList();
        String nameCheck = usersPage.getNameWishList();
//        new Actions(driver).sendKeys(Keys.SPACE).perform();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        assertThat(nameCheck)
                .as("Элемент с названием %s не найден", nameProduct)
                .isEqualTo(nameProduct);


        usersPage.isDeleteWishList();
//
        Boolean isDeletePresent = usersPage.isDeleteWishList();
        assertThat(isDeletePresent).as("Проверка удаления элемента").isTrue();

    }
}