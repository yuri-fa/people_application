package com.yuri.menssageria.consumer;

import br.com.springkafka.People;
import com.yuri.menssageria.domain.Book;
import com.yuri.menssageria.domain.PeopleRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
public class PeopleConsumer {

    @Autowired
    private PeopleRepository peopleRepository;

    @KafkaListener(topics = "${topic.name}")
    public void consumer(ConsumerRecord<String, People> consumerRecord, Acknowledgment acknowledgment){

        var people = consumerRecord.value();
        log.info("Mensagem recebida: " +people.toString());
        var peopleEntity = com.yuri.menssageria.domain.People.builder().build();
        peopleEntity.setId(people.getId().toString());
        peopleEntity.setCpf(people.getCpf().toString());
        peopleEntity.setNome(people.getName().toString());
        peopleEntity.setBooks(people.getBooks()
                .stream()
                .map(book-> Book.builder()
                        .people(peopleEntity)
                        .name(book.toString()).build())
                        .collect(Collectors.toList()));
        peopleRepository.save(peopleEntity);
        acknowledgment.acknowledge();
    }
}
