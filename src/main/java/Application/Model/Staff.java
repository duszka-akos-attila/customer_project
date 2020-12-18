package Application.Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Staff {
    private final String firstName;
    private final String lastName;
    private final String address;
    //private Blob picture;
    private final String email;
    private final String storeAddress;
    private final int active;
    private final String username;
    private final String password;
}
