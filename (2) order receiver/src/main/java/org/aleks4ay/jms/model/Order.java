package org.aleks4ay.jms.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {

    private final int orderId;
    private final Good good;
    private final User user;

    @JsonCreator
    public Order(@JsonProperty("orderId") int orderId, @JsonProperty("good") Good good, @JsonProperty("user") User user) {
        this.orderId = orderId;
        this.good = good;
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public Good getGood() {
        return good;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", good=" + good +
                ", user=" + user +
                '}';
    }
}
