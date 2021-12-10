package praktikum.Courier;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.Client.Courier;
import praktikum.Client.CourierClient;
import praktikum.Client.CourierCredentials;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CourierLoginTest {
    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Courier.getRandom();
    }
    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }


    @Test
    public void checkCourierCanLogIn() {

        courierClient.create(courier);
        ValidatableResponse response = courierClient.login(new CourierCredentials(courier.login, courier.password));

        courierId = response.extract().path("id");
        int statusCodeResponse = response.extract().statusCode();

        assertThat("Courier ID is incorrect", courierId, is(not(0)));
        assertThat(statusCodeResponse, equalTo(200));
    }

}
