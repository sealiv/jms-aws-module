package org.aleks4ay.jms.model;

import org.aleks4ay.jms.util.Constants;

public class CounterItem implements Good {

    private final int counterItemId;
    private final int quantity;

    public CounterItem(int quantity) {
        this.counterItemId = Constants.getNextCountableItemId();
        this.quantity = quantity;
    }

    public int getCounterItemId() {
        return counterItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + counterItemId +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
