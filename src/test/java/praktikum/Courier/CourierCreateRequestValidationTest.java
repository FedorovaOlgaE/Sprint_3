package praktikum.Courier;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Client.Courier;
import praktikum.Client.CourierClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class CourierCreateRequestValidationTest {

    private final Courier courier;
    private final int expectedStatus;
    private final String expectedErrorMessage;

    public CourierCreateRequestValidationTest(Courier courier, int expectedStatus, String expectedErrorMessage) {
        this.courier = courier;
        this.expectedStatus = expectedStatus;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {Courier.getWithLoginOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithPasswordOnly(), 400, "Недостаточно данных для создания учетной записи"},
                {Courier.getWithPasswordAndLogin(), 201, null}
        };
    }

    @Test
    public void invalidRequestIsNotAllowed(){
        ValidatableResponse response = new CourierClient().create(courier);

        int statusCode = response.extract().statusCode();
        String bodyErrorMessage = response.extract().path("message");

        assertThat(statusCode, (equalTo(expectedStatus)));
        assertThat(bodyErrorMessage, equalTo(expectedErrorMessage));

    }
}
