package Model;

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
    private int addressId;
    private String address;
    private String address2;
    private String district;
    private int cityId;
    private String postalCode;
    private String phone;
    private Timestamp lastUpdate;
}
