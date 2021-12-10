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
import static org.junit.Assert.assertTrue;

public class CourierCreateTest {

    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    @Before
    public void SetUp(){
        courier = Courier.getRandom();
        courierClient = new CourierClient();
    }
    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    public void checkCourierCanBeCreated(){
        ValidatableResponse response = courierClient.create(courier);
        courierId = courierClient.login(new CourierCredentials(courier.login, courier.password)).extract().path("id");

        int statusCode = response.extract().statusCode();
        boolean isCourierCreated = response.extract().path("ok");

        assertTrue("Courier is not created", isCourierCreated);
        assertThat("Status code is incorrect", statusCode, equalTo(201));
        assertThat("Courier ID is incorrect",courierId, is(not(0)));
    }

}
