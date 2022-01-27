package com.yuri.menssageria.controller;

import br.com.springkafka.People;
import com.yuri.menssageria.producer.PeopleProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/peoples")
@AllArgsConstructor
public class PeopleController {

    private final PeopleProducer peopleProducer;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendMessage(@RequestBody PeopleDTO peopleDTO){
        var id = UUID.randomUUID().toString();
        var message = People.newBuilder()
                .setId(id)
                .setCpf(peopleDTO.getCpf())
                .setName(peopleDTO.getNome())
                .setBooks(peopleDTO.getBooks().stream().map(p-> (CharSequence) p).collect(Collectors.toList()))
                .build();
        peopleProducer.send(message);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
