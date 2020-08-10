package ru.open.swagger.steps;

import com.github.viclovsky.swagger.coverage.SwaggerCoverageRestAssured;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class Steps {

    private static final Logger LOG = LoggerFactory.getLogger(Steps.class);

    private String baseUrl;

    public Steps(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .build();
    }

    @Step("Пользователь выполняет POST-запрос на URL: {endpoint}")
    public Response httpPost(String endpoint, Object body) {
        LOG.info("POST to {}{}", baseUrl, endpoint);
        return given()
                .filter(new SwaggerCoverageRestAssured())
                .spec(getRequestSpecification())
                .body(body)
                .log().all()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
    }

    @Step("Пользователь выполняет GET-запрос на URL: {endpoint}")
    public Response httpGet(String endpoint) {
        LOG.info("GET to {}{}", baseUrl, endpoint);
        return given()
                .filter(new SwaggerCoverageRestAssured())
                .spec(getRequestSpecification())
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
    }

}
