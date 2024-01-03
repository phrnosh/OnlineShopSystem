package mft.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import mft.model.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString

public class Orders {
    private int id;
    private Customer customer;
    private OrderDetails items;
    private float amount;
    private float discount;
    private Payment payment;
    private LocalDateTime orderDate;
    private OrderStatus status;
}
