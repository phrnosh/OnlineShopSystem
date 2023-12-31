package mft.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import mft.model.entity.enums.PaymentType;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString

public class Payment {
    private int id;
    private Double totalCost;
    private PaymentType PaymentType;
    private String PaymentDetails;
    private LocalDateTime PaymentTimeStamp;
}
