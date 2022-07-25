package org.aleks4ay.jms.sender;

import org.aleks4ay.jms.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsSender {

    private static final String ORDER_QUEUE = "order.queue";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(Order order){
        jmsTemplate.convertAndSend(ORDER_QUEUE, order, message -> {
            message.setStringProperty("orderState", "NEW");
            return message;
        });
    }
}
