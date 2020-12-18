package Application.Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Address {
    private final String address;
    private final String address2;
    private final String district;
    private final String city;
    private final String country;
    private final String postalCode;
    private final String phone;
}
