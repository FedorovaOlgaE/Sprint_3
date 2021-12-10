package praktikum.Courier;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import praktikum.Client.Courier;
import praktikum.Client.CourierClient;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToObject;

public class DuplicateCourierCannotBeCreatedTest {

    private Courier courier;
    private CourierClient courierClient;

    @Before
    public void SetUp(){
        courier = Courier.getRandom();
        courierClient = new CourierClient();
    }

    @Test
    public void duplicateCourierCannotBeCreated(){
        courierClient.create(courier);
        ValidatableResponse response = courierClient.create(courier);

        int statusCodeError = response.extract().statusCode();
        String errorMessage = response.extract().path("message");
        assertThat("Status code is incorrect", statusCodeError, equalTo(409));
        assertThat("Courier is duplicate", errorMessage, equalToObject("Этот логин уже используется. Попробуйте другой."));


    }
}
