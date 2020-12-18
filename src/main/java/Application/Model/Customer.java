package Application.Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Customer {
    private final String storeAddress;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final int active;
}
