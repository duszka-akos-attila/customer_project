package Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateDto {
    private int updatedStoreId;
    private String updatedFirstName;
    private String updatedLastName;
    private String updatedEmail;
    private int updatedAddressId;
    private int updatedActive;
}
