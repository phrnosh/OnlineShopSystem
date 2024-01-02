package mft.model.entity;

import com.google.gson.Gson;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.log4j.Log4j;

@Setter
@Getter
@SuperBuilder(toBuilder = true)

@Log4j
public class Customer {
    private int id;
    private String name;
    private String family;
    private String username;
    private String password;
    private String address;
    private String phoneNumber;
    private String email;
    private boolean status;

    public Customer() {
        log.info("Customer Created");
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
