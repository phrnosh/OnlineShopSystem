package mft.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString

public class OnlinePayment extends Payment{
    private String paymentDetails;
    private String cartNumber;
}
