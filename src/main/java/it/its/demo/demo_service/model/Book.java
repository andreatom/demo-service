package it.its.demo.demo_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

//   Questo è il lato che possiede la relazione: genera la colonna fk_author_id nella tabella book
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_author_id")
    Author author;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Transaction> transactions = new ArrayList<>();
}

