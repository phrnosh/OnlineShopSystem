package mft.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j;
import mft.model.entity.enums.PaymentType;

import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder(toBuilder = true)

@Log4j
public class Payment {
    private int id;
    private Double totalCost;
    private String PaymentType;
    private String PaymentDetails;
    private LocalDateTime PaymentTimeStamp;

    public Payment() {
        log.info("Payment Created");
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
