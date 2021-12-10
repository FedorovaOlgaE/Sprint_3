package praktikum.Order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.Client.RestAssuredClient;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredClient {
    private static final String ORDERS_PATH = "/api/v1/orders/";

    @Step("Create order")
    public ValidatableResponse create(Order order){
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(ORDERS_PATH)
                .then();

    }

    @Step("Get all orders")
    public ValidatableResponse getAll(){
        return given()
                .spec(getBaseSpec())
                .when()
                .get(ORDERS_PATH)
                .then();

    }

}
