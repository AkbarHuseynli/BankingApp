package org.example.notification.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public NewTopic notificationTopic(){
        return TopicBuilder.name("notify").build();
    }
}
