package org.example.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "notify", groupId = "groupId")
    public String listener(String data){
        System.out.println("Listnerss");
        return data;
    }
}
