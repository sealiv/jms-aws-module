package org.aleks4ay.jms.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Liquid implements Good{

    private final int liquidId;
    private final double volume;

    @JsonCreator
    public Liquid(
            @JsonProperty("liquidId") int liquidId,
            @JsonProperty("volume") double volume) {
        this.liquidId =liquidId;
        this.volume = volume;
    }

    public int getLiquidId() {
        return liquidId;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Liquid{" +
                "liquidId=" + liquidId +
                ", volume= " + volume + " liters" +
                '}';
    }

}
