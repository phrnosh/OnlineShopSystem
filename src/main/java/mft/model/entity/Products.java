package mft.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j;

@Setter
@Getter
@SuperBuilder(toBuilder = true)

@Log4j
public class Products {
    private int id;
    private String name;
    private String brand;
    private String size;
    private Double price;
    private String description;

    public Products() {
        log.info("Products Created");
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
