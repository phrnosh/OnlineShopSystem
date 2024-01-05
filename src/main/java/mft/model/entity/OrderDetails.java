package mft.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j;

@Setter
@Getter
@SuperBuilder(toBuilder = true)

@Log4j
public class OrderDetails {
    private int id;
    private Products products;
    private int quantity;

    public OrderDetails() {
        log.info("OrderDetails Created");
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
