package Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddressUpdateDto {
    private String updatedAddress;
    private String updatedAddress2;
    private String updatedDistrict;
    private int updatedCityId;
    private String updatedPostalCode;
    private String updatedPhone;
}
