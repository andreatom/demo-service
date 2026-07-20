package it.its.demo.demo_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //Specifichiamo la strategia con cui è stato generato l'id
    String id;

    String name;
    Integer quantity;
    Float price;

    @ManyToOne
    @JoinColumn(name = "fk_author_id")
    @JsonBackReference
    Author author;
}

/*
*
* {
 *   "id": "1234",
 *   "name": "Il piccolo principe",
 *   "author": "Antoine"
* }

* */
