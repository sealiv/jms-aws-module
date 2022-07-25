package org.aleks4ay.jms.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CounterItem implements Good {

    private final int counterItemId;
    private final int quantity;

    @JsonCreator
    public CounterItem(
            @JsonProperty("counterItemId") int counterItemId,
            @JsonProperty("quantity") int quantity) {
        this.counterItemId = counterItemId;
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
        return "CounterItem{" +
                "counterItemId=" + counterItemId +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
