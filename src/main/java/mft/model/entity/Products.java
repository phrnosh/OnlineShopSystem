package mft.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString

public class Products {
    private int id;
    private String name;
    private String brand;
//    private ProductGrouping group;
    private float size;
    private float price;
    private String description;
}
