package praktikum.Courier;


import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Client.CourierClient;
import praktikum.Client.CourierCredentials;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class CourierLoginRequestValidationTest {

    private final CourierCredentials courierCredentials;
    private final int expectedStatus;
    private final String expectedErrorMessage;

    public CourierLoginRequestValidationTest(CourierCredentials courierCredentials, int expectedStatus, String expectedErrorMessage) {
        this.courierCredentials = courierCredentials;
        this.expectedStatus = expectedStatus;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {CourierCredentials.LoginOnly(), 400, "Недостаточно данных для входа"},
                {CourierCredentials.getWithPasswordOnly(), 400, "Недостаточно данных для входа"},
                {CourierCredentials.getWithDoNotReallyLoginAndPassword(), 404, "Учетная запись не найдена"},

        };
    }


    @Test
    public void loginCouriersWithoutFields() {
        ValidatableResponse response = new CourierClient().login(courierCredentials);

        int statusCode = response.extract().statusCode();
        String bodyErrorMessage = response.extract().path("message");

        assertThat(statusCode, equalTo(expectedStatus));
        assertThat(bodyErrorMessage, equalTo(expectedErrorMessage));
    }
    //при логине, если передать только логин - ошибка 504 - баг
}
