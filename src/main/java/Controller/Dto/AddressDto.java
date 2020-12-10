package Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String address;
    private String address2;
    private String district;
    private int cityId;
    private String postalCode;
    private String phone;
    private Timestamp lastUpdate;
}
