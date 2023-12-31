package mft.model.entity;

import jdk.nashorn.internal.runtime.Debug;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@ToString

public class User {
    private int id;

    @Setter(AccessLevel.NONE)
    private String username;

    private String password;

    private boolean status;

}
