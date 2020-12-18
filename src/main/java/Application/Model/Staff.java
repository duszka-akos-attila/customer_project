package Application.Model;

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
    private String address;
    //private Blob picture;
    private String email;
    private String storeAddress;
    private int active;
    private String username;
    private String password;
}
