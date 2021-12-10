package praktikum.Order;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class OrderListTest {
    @Test
    public void GetOrdersReturnTest(){
        OrderClient orderClient = new OrderClient();
        ValidatableResponse response = orderClient.getAll();
        List<Object> orders = response.extract().jsonPath().getList("orders");
        assertFalse(orders.isEmpty());
    }
}
