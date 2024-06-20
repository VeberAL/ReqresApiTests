package reqres.tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import reqres.models.*;
import reqres.specs.UserSpecs;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static reqres.specs.UserSpecs.requestSpec;
import static reqres.specs.UserSpecs.responseSpecClientStatus400;

@DisplayName("Проверка api запросов сайта https://reqres.in.")
public class ApiTests extends TestBase {

    @Feature("Тестирование валидации пользователя при вводе неверного пароля.")
    @Test
    @Tag("account")
    @DisplayName("Проверка валидации пользователя.")
    void unsuccessfulPasswordTest() {
        UserBodyModel authData = new UserBodyModel();
        authData.setEmail("aleo83@rambler.ru");

        UserLoginErrorResponseModel responseBody = step("Отправление запроса на валидацию пользователя.", () ->
                given(requestSpec)
                        .body(authData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpecClientStatus400)
                        .extract().as(UserLoginErrorResponseModel.class));
        step("Проверка ответа о неверном пароле.", () ->
                assertEquals("Missing password", responseBody.getError()));
    }

    @Feature("Тестирование api запроса на наличие email-адреса заданного пользователя.")
    @Test
    @Tag("user")
    @DisplayName("Проверка email-адреса пользователя.")
    void singleEmailTest() {
        UserDataResponseModel data = step("Отправление запроса на получение данных пользователя.", () ->
                given(requestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(UserSpecs.responseSpecOkStatus200)
                        .extract().as(UserDataResponseModel.class));

        step("Проверка ответа с данными email-адреса пользователя.", () ->
                assertThat(data.getUser().getEmail()).isEqualTo("janet.weaver@reqres.in"));
    }

    @Feature("Тестирование api запроса на наличие названия цвета в пользовательской базе.")
    @Test
    @Tag("user")
    @DisplayName("Проверка наличия выбранного наименования цвета")
    void singleColourTest() {
        step("Выполнение запроса на получение информации о цвете", () ->
                given(requestSpec)
                        .when()
                        .get("/unknown")
                        .then()
                        .spec(UserSpecs.responseSpecOkStatus200)
                        .body("data.findAll{it.name =~/./}.name.flatten()",
                                hasItem("aqua sky")));
    }

    @Feature("Тестирование api запроса на создание пользователя.")
    @Test
    @Tag("account")
    @DisplayName("Создание нового пользователя.")
    void createUserTest() {
        UserCreateBodyModel createBody = new UserCreateBodyModel();
        createBody.setName("Alex Veber");
        createBody.setJob("QA");
        UserCreateResponseModel createResponse = step("Отправка запроса на создание пользователя.", () ->
                given(requestSpec)
                        .body(createBody)
                        .when()
                        .post("/users")
                        .then()
                        .spec(UserSpecs.responseSpecOkStatus201)
                        .extract().as(UserCreateResponseModel.class));

        step("Проверка ответа с данными о созданном пользователе.", () -> {
            assertThat(createResponse.getName()).isEqualTo("Alex Veber");
            assertThat(createResponse.getJob()).isEqualTo("QA");
        });
    }

    @Feature("Тестирование api запроса о получении данных пользователя.")
    @Test
    @Tag("user")
    @DisplayName("Проверка информации о пользователе.")
    void singleNameTest() {
        UserDataResponseModel data = step("Создание запроса на получение данных пользователя.", () ->
                given(requestSpec)
                        .when()
                        .get("/users/2")
                        .then()
                        .spec(UserSpecs.responseSpecOkStatus200)
                        .extract().as(UserDataResponseModel.class));

        step("Проверка ответа о данных пользователя.", () -> {
            assertThat(data.getUser().getFirstName()).isEqualTo("Janet");
            assertThat(data.getUser().getLastName()).isEqualTo("Weaver");
        });
    }

    @Feature("Тестирование api запроса на удаление данных.")
    @Test
    @Tag("account")
    @DisplayName("Удаление данных о пользователе.")
    void deleteUserTest() {
        step("Отправка запроса на удаление пользователя.", () ->
                given(requestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(UserSpecs.responseSpecOkStatus204));
    }

    @Feature("Тестирование api запроса на отсутствие ресурса.")
    @Test
    @Tag("user")
    @DisplayName("Проверка отсутствия ресурса.")
    void singleResourceNotFoundTest() {
        step("Отправка запроса о проверке отсутствующего ресурса.", () ->
                given(requestSpec)
                        .when()
                        .get("/unknown/23")
                        .then()
                        .spec(UserSpecs.responseSpecClientStatus404));
    }

}
