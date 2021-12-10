package praktikum.Client;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierCredentials {

    public String login;
    public String password;

    public CourierCredentials(){}

    public CourierCredentials(String login, String password){
        this.login = login;
        this.password = password;
    }
    public CourierCredentials setLogin(String login) {
        this.login = login;
        return this;
    }

    public CourierCredentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public static CourierCredentials LoginOnly() {
        return new CourierCredentials().setLogin(RandomStringUtils.randomAlphabetic(10));
    }

    public static CourierCredentials getWithPasswordOnly() {
        return new CourierCredentials().setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    public static CourierCredentials getWithDoNotReallyLoginAndPassword() {
        return new CourierCredentials().setLogin(RandomStringUtils.randomAlphabetic(10)).setPassword(RandomStringUtils.randomAlphabetic(10));
    }
}
