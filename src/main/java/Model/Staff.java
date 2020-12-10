package Model;

import lombok.*;

import java.sql.Blob;
import java.sql.Timestamp;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Staff {
    private String firstName;
    private String lastName;
    private int addressId;
    private Blob picture;
    private String email;
    private int storeId;
    private int active;
    private String username;
    private String password;
    private Timestamp lastUpdate;
}
