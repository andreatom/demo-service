package it.its.demo.demo_service.repository;

import it.its.demo.demo_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    public List<Book> findByName(String name);

    @Query("select b from Book b where b.name = :name")
    public List<Book> findByNameWithQuery(@Param("name") String name);

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
//
//    public List<Book> findByName(String name){
//        return books.stream()
//                .filter(book -> book.getName().equals(name))
//                .collect(Collectors.toList());
//    }
//
//    public int delete(String id){
//
//        Optional<Book> book = findById(id);
//
//        if(book.isPresent()){
//            books.removeIf(it -> it.getId().equals(id));
//            return 1;
//        }
//        else {
//            return 0;
//        }
//
//    }
//
//    public int update(String id, Book updated) {
//        Optional<Book> optionalBook = findById(id);
//
//        if (optionalBook.isEmpty()) {
//            return 0;
//        }
//
//        Book book = optionalBook.get();
//        book.setName(updated.getName());
//        book.setAuthor(updated.getAuthor());
//        book.setQuantity(updated.getQuantity());
//        return 1;
//
//    }
}
