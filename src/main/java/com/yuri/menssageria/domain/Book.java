package com.yuri.menssageria.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy="uuid2")
    private String id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private People people;



}
