package it.its.demo.demo_service.dto.book;

import it.its.demo.demo_service.dto.category.InnerCategoryDto;
import it.its.demo.demo_service.dto.transaction.InnerTransactionDto;
import it.its.demo.demo_service.dto.transaction.ResTransactionDto;
import it.its.demo.demo_service.dto.transaction.TransactionTotalDto;
import it.its.demo.demo_service.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResBookDto {
    String id;
    String name;
    InnerAuthorDto author;
    List<ResTransactionDto> transazioni;
    Integer quantity;
    Float guadagno;
    Float price;
    List<InnerCategoryDto> categories;
}
