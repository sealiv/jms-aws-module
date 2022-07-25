package org.aleks4ay.jms.util;

public class Constants {
    private static int liquidId = 100;
    private static int countableItemId = 100;
    private static int userId = 100;
    private static int orderId = 100;

    public static int getNextLiquidId() {
        return ++liquidId;
    }

    public static int getNextCountableItemId() {
        return ++countableItemId;
    }

    public static int getNextUserId() {
        return ++userId;
    }

    public static int getNextOrderId() {
        return ++orderId;
    }
}
