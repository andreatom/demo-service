package it.its.demo.demo_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;

//    Questa annotazione dice ad Hybernate di non creare un'altra colonna o tabella perchè questo lato della relazione è
//    il lato "padre" della relazione, quindi non ha bisogno di una colonna per la relazione.
//    La colonna per la relazione sarà creata nella tabella "Book" con la colonna "fk_author_id"
//    che fa riferimento alla tabella "Author".
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Book> books = new ArrayList<>();
}
