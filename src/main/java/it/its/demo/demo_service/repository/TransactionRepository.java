package it.its.demo.demo_service.repository;

import it.its.demo.demo_service.model.Book;
import it.its.demo.demo_service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByBook(Book book);
}
