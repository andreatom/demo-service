package it.its.demo.demo_service.repository;

import it.its.demo.demo_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, String> { //Il primo parametro è il tipo dell'entità, il secondo è il tipo dell'id

//     List<Book> findByName(String name);

    public List<Book> findByName(String name);

//     I due punti nella query indicano che si tratta di un parametro
     @Query("SELECT b FROM Book b WHERE b.name = :name")
     List<Book> findByNameWithQuery(@Param("name") String name);

}
