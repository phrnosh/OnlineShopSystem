package mft.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString

public class ShoppingCart {
    private int id;
    private int productID;
    private String productName;
    private int quantity;
    private Double totalCost;
    private int price;
    private String description;
}
