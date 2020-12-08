package Model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Staff {
    private int staffId;
    private String firstName;
    private String lastName;
    private int addressId;
    private byte[] picture;
    private String email;
    private int storeId;
    private int active;
    private String username;
    private String password;
    private Date lastUpdate;
}
