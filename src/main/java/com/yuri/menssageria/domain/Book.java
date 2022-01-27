package com.yuri.menssageria.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy="uuid2")
    private String id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private People people;



}
