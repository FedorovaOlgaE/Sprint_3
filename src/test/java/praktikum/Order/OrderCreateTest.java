package praktikum.Order;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class OrderCreateTest {

    private OrderClient orderClient;
    Order order;
    private String[] color;

    public OrderCreateTest(String[] color){

        this.color = color;
    }


    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {new String[] {"BLACK"}},
                {new String[] {"GREY"}},
                {new String[] {"BLACK\", \"GREY"}},
                {null}
        };
    }

    @Test
    public void orderCanBeCreated(){
        orderClient  = new OrderClient();
        order = Order.getRandom().SetColor(color);
        ValidatableResponse response = orderClient.create(order);

        int track  = response.extract().path("track");
        int statusCodeResponse = response.extract().statusCode();

        assertThat(statusCodeResponse, equalTo(201));
        assertThat("Order is not created", track, is(not(0)));
    }
}
