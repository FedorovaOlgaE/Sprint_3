package praktikum.Order;

import org.apache.commons.lang3.RandomStringUtils;

public class Order {
    public final String firstName;
    public final String lastName;
    public final String address;
    public final String metroStation;
    public final String phone;
    public final int rentTime;
    public final String deliveryDate;
    public final String comment;
    public String[] color;

    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }


    public static Order getRandom(){
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        final String lastName = RandomStringUtils.randomAlphabetic(10);
        final String address = "ул. Академика Королева, 12";
        final String metroStation = RandomStringUtils.randomAlphabetic(10);
        final String phone = RandomStringUtils.randomNumeric(11);
        final int rentTime = 14;
        final String deliveryDate = "2021-12-12";
        final String comment = RandomStringUtils.randomAlphabetic(10);
        final String[] color = new String[]{};
        return new Order(firstName, lastName, address,metroStation, phone, rentTime, deliveryDate, comment,color);
    }
    public Order SetColor(String[] color){
        this.color = color;
        return this;
    }
}
