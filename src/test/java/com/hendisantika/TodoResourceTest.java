package com.hendisantika;

import com.hendisantika.virtualthreads.entity.Todo;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit5.virtual.ShouldNotPin;
import io.quarkus.test.junit5.virtual.VirtualThreadUnit;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-virtual-threads-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/17/23
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@VirtualThreadUnit
@ShouldNotPin
public class TodoResourceTest {
    @Test
    @Order(1)
    void testInitialItems() {
        List<Todo> todos = get("/api").then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON)
                .extract().body().as(getTodoTypeRef());
        assertEquals(4, todos.size());

        get("/api/1").then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("title", is("Introduction to Quarkus"))
                .body("completed", is(true));
    }

    @Test
    @Order(2)
    void testAddingAnItem() {
        Todo todo = new Todo();
        todo.title = "testing the application";
        given()
                .body(todo)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .post("/api")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("title", is(todo.title))
                .body("completed", is(false))
                .body("id", is(5));

        List<Todo> todos = get("/api").then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON)
                .extract().body().as(getTodoTypeRef());
        assertEquals(5, todos.size());
    }

    @Test
    @Order(3)
    void testUpdatingAnItem() {
        Todo todo = new Todo();
        todo.title = "testing the application (updated)";
        todo.completed = true;
        given()
                .body(todo)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .pathParam("id", 5)
                .when()
                .patch("/api/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("title", is(todo.title))
                .body("completed", is(true))
                .body("id", is(5));
    }

    @Test
    @Order(4)
    void testDeletingAnItem() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .pathParam("id", 5)
                .when()
                .delete("/api/{id}")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

        List<Todo> todos = get("/api").then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(MediaType.APPLICATION_JSON)
                .extract().body().as(getTodoTypeRef());
        assertEquals(4, todos.size());
    }

}
