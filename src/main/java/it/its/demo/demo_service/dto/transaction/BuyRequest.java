package it.its.demo.demo_service.dto.transaction;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BuyRequest {

    @Positive
    Integer quantity;
}
