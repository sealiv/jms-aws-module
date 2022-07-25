package org.aleks4ay.jms.receiver;

import org.aleks4ay.jms.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsSender {

    private static final String ORDER_QUEUE_ACCESS = "order.queue.access";
    private static final String ORDER_QUEUE_REJECT = "order.queue.reject";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(Order order, boolean access){
        String destinationName = access ? ORDER_QUEUE_ACCESS : ORDER_QUEUE_REJECT;
        String orderState = access ? "ACCESS" : "REJECT";
        jmsTemplate.convertAndSend(destinationName, order, message -> {
            message.setStringProperty("orderState", orderState);
            return message;
        });
    }
}
