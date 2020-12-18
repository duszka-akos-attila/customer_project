package Application.Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddressUpdateDto extends AddressDto {
    private String updatedAddress;
    private String updatedAddress2;
    private String updatedDistrict;
    private String updatedCity;
    private String updatedCountry;
    private String updatedPostalCode;
    private String updatedPhone;
}
