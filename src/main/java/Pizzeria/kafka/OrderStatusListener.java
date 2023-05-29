package Pizzeria.kafka;

import client.model.Notification;
import client.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//import javax.management.Notification;


@Component
public class OrderStatusListener {

    private final KafkaTemplate<Long, Notification> kafkaTemplate;

    public OrderStatusListener(KafkaTemplate<Long, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-topic", groupId = "order-group")
    public void listen(Order updatedOrder) {
        Notification message = new Notification(updatedOrder.getId(), "Ready" );
        kafkaTemplate.send("notification-topic", message);
    }
}
