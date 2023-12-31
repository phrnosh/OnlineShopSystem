package mft.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j;
import mft.model.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Setter
@Getter
@SuperBuilder(toBuilder = true)

@Log4j
public class Orders {
    private int id;
    private Customer customer;
    private OrderDetails items;
    private Double amount;
    private float discount;
    private Payment payment;
    private LocalDateTime orderDate;
    private OrderStatus status;

    public Orders() {
        log.info("Orders Created");
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
