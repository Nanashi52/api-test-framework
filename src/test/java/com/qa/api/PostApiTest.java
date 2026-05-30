package com.qa.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JSONPlaceholder API — тесты постов")
class PostApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    @DisplayName("GET /posts — статус 200 и 100 постов")
    void getPostsReturnsList() {
        given()
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", is(100));
    }

    @Test
    @DisplayName("GET /posts/1 — корректные поля")
    void getPostById() {
        given()
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("id", is(1))
            .body("title", not(emptyString()))
            .body("body", not(emptyString()));
    }

    @Test
    @DisplayName("GET /posts?userId=1 — фильтрация по пользователю")
    void getPostsByUserId() {
        given()
            .queryParam("userId", 1)
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("userId", everyItem(is(1)));
    }

    @Test
    @DisplayName("POST /posts — создание нового поста")
    void createPost() {
        Map<String, Object> newPost = Map.of(
                "title", "QA Test Post",
                "body", "This is a test post created by API test",
                "userId", 1
        );

        Response response = given()
            .contentType(ContentType.JSON)
            .body(newPost)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", is("QA Test Post"))
            .body("body", is("This is a test post created by API test"))
            .body("userId", is(1))
            .body("id", notNullValue())
            .extract()
            .response();

        int createdId = response.jsonPath().getInt("id");
        assertTrue(createdId > 0, "Созданный пост должен иметь id");
    }

    @Test
    @DisplayName("PUT /posts/1 — обновление поста")
    void updatePost() {
        Map<String, Object> updatedPost = Map.of(
                "id", 1,
                "title", "Updated Title",
                "body", "Updated body content",
                "userId", 1
        );

        given()
            .contentType(ContentType.JSON)
            .body(updatedPost)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200)
            .body("title", is("Updated Title"))
            .body("body", is("Updated body content"));
    }

    @Test
    @DisplayName("DELETE /posts/1 — удаление поста")
    void deletePost() {
        given()
        .when()
            .delete("/posts/1")
        .then()
            .statusCode(200);
    }
}
