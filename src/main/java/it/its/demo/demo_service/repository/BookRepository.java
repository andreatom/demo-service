package it.its.demo.demo_service.repository;

import it.its.demo.demo_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;

@Repository
public interface BookRepository extends JpaRepository<Book, String> { //Il primo parametro è il tipo dell'entità, il secondo è il tipo dell'id

//    private final List<Book> books = new ArrayList<>();
//
//    public Book save(Book book){
//        books.add(book);
//        return book;
//    }
//
//    public Optional<Book> findById(String id){
//        return books.stream()
//                .filter(book -> book.getId().equals(id))
//                .findFirst();
//    }
//
//    public List<Book> findAll(){
//        return books;
//    }

     List<Book> findByName(String title);


}
