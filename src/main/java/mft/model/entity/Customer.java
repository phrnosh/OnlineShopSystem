package mft.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString

public class Customer {
    private int id;
    private String name;
    private String family;
    private String address;
    private String phoneNumber;
    private String email;
}
