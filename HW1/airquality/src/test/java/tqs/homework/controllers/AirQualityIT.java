package tqs.homework.controllers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AirQualityIT {
    @LocalServerPort
    private Integer port;

    @Test
    void whenGetAirQualityByCity_thenStatus200() {
        String uri = "http://localhost:" + port + "/api/airquality/current_city?city=London";
        given()
        .when()
            .get(uri)
        .then()
            .statusCode(200)
            .body("name", equalTo("London"));
    }

    @Test
    void whenGetAirQualityByBadCity_thenStatus404() {
        String uri = "http://localhost:" + port + "/api/airquality/current_city?city=abc";
        given()
        .when()
            .get(uri)
        .then()
            .statusCode(404);
    }
}
