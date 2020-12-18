package Application.Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Address {
    private String address;
    private String address2;
    private String district;
    private String city;
    private String country;
    private String postalCode;
    private String phone;
}
