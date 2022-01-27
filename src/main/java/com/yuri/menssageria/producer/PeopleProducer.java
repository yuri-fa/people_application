package com.yuri.menssageria.producer;

import br.com.springkafka.People;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@Configuration
public class PeopleProducer {

    private final String topicName;
    private final KafkaTemplate<String, People> kafkaTemplate;

    public PeopleProducer(@Value("${topic.name}") String topicName, KafkaTemplate<String, People> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(People people){
        kafkaTemplate.send(this.topicName,people).addCallback(
                sucess -> log.info("Send message"),
                failure -> log.error("Not send message" + failure.getMessage())
        );
    }
}
