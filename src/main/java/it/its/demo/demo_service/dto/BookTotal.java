package it.its.demo.demo_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookTotal {
    Double total;
    String bookId;
}
