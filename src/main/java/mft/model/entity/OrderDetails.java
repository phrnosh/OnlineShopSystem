package mft.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString

public class OrderDetails {
    private int id;
    private Products products;
    private int quantity;
}
