package com.yuri.menssageria.controller;

import lombok.Data;

import java.util.List;

@Data
public class PeopleDTO {

    private String cpf;
    private String nome;
    private List<String> books;


}
