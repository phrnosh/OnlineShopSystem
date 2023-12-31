package mft.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString

public class Account {
    private int id;
    private String bankName;
    private String accountNumber;
}
