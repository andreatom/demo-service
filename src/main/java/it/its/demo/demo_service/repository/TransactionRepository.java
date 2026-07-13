package it.its.demo.demo_service.repository;

import it.its.demo.demo_service.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public Transaction save(Transaction transaction){
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findByBookId(String id){
        return transactions.stream()
                .filter(transaction -> transaction.getBookId().equals(id))
                .collect(Collectors.toList());
    }
}
