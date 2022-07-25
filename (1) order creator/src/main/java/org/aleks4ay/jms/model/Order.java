package org.aleks4ay.jms.model;

import org.aleks4ay.jms.util.Constants;

public class Order {

    private final int orderId;
    private final Good good;
    private final User user;

    public Order(Good good, User user) {
        this.orderId = Constants.getNextOrderId();
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
        return "BookOrder{" +
                "orderId='" + orderId + '\'' +
                ", good=" + good +
                ", user=" + user +
                '}';
    }
}
