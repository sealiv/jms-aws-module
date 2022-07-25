package org.aleks4ay.jms.receiver;

import org.aleks4ay.jms.model.*;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Service
public class JmsReceiver {

    private final int maxQuantity = 100;
    private int currentQuantity;
    private final double maxVolume = 100.0;
    private double currentVolume;

    @Autowired
    private JmsSender jmsSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsReceiver.class);

    @JmsListener(destination = "order.queue")
    public void receive(ActiveMQTextMessage message){
        LOGGER.info("Message received!");
        Order order = null;
        try {
            order = parseMessageFromJson(message.getText());
            LOGGER.info("Receive New Order: " + order);

        } catch (JMSException e) {
            LOGGER.error("Exception during parsing Message ({}).", message, e);
        }

        boolean isSuccess = processReceivedOrder(order);
        if (isSuccess) {
            LOGGER.info("Order with id = '{}' was add to work.", order.getOrderId());
        } else {
            LOGGER.info("Order with id = '{}' was rejected.", order.getOrderId());
        }
    }

    private boolean processReceivedOrder (Order order) {
        if (order != null && order.getGood() instanceof Liquid){
            Liquid liquid = (Liquid) order.getGood();
            if (liquid.getVolume() <= maxVolume - currentVolume) {
                currentVolume += liquid.getVolume();
                jmsSender.send(order, true);
                return true;
            } else {
                jmsSender.send(order, false);
                return false;
            }
        } else {
            CounterItem counterItem = (CounterItem) order.getGood();
            if (counterItem.getQuantity() <= maxQuantity - currentQuantity) {
                currentQuantity += counterItem.getQuantity();
                jmsSender.send(order, true);
                return true;
            } else {
                jmsSender.send(order, false);
                return false;
            }
        }
    }

    public Order parseMessageFromJson(String jsonOrder) {
        JSONObject obj = new JSONObject(jsonOrder);

        int orderId = obj.getInt("orderId");

        JSONObject userObject = obj.getJSONObject("user");
        User user = new User(userObject.getInt("userId"), userObject.getString("name"));

        JSONObject goodObject = obj.getJSONObject("good");
        Good good;

        if (!goodObject.isNull("liquidId")) {
            good = new Liquid(goodObject.getInt("liquidId"), goodObject.getDouble("volume"));
        } else {
            good = new CounterItem(goodObject.getInt("counterItemId"), goodObject.getInt("quantity"));
        }
        return new Order(orderId, good, user);
    }
}
