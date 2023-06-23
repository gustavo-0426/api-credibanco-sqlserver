package com.co.softworld.credibanco.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Product {

    @Id
    private String id;
    private String name;
}