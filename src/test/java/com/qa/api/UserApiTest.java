package com.qa.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JSONPlaceholder API — тесты пользователей")
class UserApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    @DisplayName("GET /users — статус 200")
    void getUsersReturns200() {
        given()
        .when()
            .get("/users")
        .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("GET /users — возвращает 10 пользователей")
    void getUsersReturns10Users() {
        given()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("size()", is(10));
    }

    @Test
    @DisplayName("GET /users/1 — корректные поля")
    void getUserById() {
        given()
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .body("id", is(1))
            .body("name", not(emptyString()))
            .body("email", containsString("@"))
            .body("address.city", not(emptyString()))
            .body("company.name", not(emptyString()));
    }

    @Test
    @DisplayName("GET /users/1 — извлечение полей через Response")
    void getUserByIdExtractFields() {
        Response response = given()
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .extract()
            .response();

        assertEquals(1, response.jsonPath().getInt("id"));
        assertNotNull(response.jsonPath().getString("name"));
        assertTrue(response.jsonPath().getString("email").contains("@"));
    }

    @Test
    @DisplayName("GET /users/999 — несуществующий пользователь")
    void getNonExistentUser() {
        given()
        .when()
            .get("/users/999")
        .then()
            .statusCode(404);
    }
}
