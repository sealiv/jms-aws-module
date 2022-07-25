package org.aleks4ay.jms.model;

import org.aleks4ay.jms.util.Constants;

public class Liquid implements Good {

    private final int liquidId;
    private final double volume;

    public Liquid(double volume) {
        this.liquidId = Constants.getNextLiquidId();
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
