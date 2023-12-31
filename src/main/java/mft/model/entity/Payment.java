package mft.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString

public abstract class Payment {
    private int id;
    private Double totalCost;
//todo    private String PaymentType;
    private LocalDateTime PaymentTimeStamp;
    private Account destinationAccount;
}
