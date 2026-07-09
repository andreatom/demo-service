package it.its.demo.demo_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    String id;
    String name;
    String author;
    Integer quantity;
}

/*
*
* {
 *   "id": "1234",
 *   "name": "Il piccolo principe",
 *   "author": "Antoine"
* }

* */
