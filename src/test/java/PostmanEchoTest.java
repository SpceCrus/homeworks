import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostmanEchoTest {

    @Test
    void shouldTestGetRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }

    @Test
    void shouldTestPostRawText() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain; charset=utf-8")
                .body("some data")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("some data"));
    }

    @Test
    void shouldTestPostFormData() {
        given()
                .baseUri("https://postman-echo.com")
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"));
    }

    @Test
    void shouldTestPutRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .body("updated info")
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("data", equalTo("updated info"));
    }

    @Test
    void shouldTestPatchRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .body("patch info")
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo("patch info"));
    }

    @Test
    void shouldTestDeleteRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .when()
                .delete("/delete")
                .then()
                .statusCode(200);
    }
}