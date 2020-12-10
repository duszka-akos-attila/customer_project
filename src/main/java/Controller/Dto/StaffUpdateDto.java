package Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Blob;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StaffUpdateDto {
    private String updatedFirstName;
    private String updatedLastName;
    private int updatedAddressId;
    private Blob updatedPicture;
    private String updatedEmail;
    private int updatedStoreId;
    private int updatedActive;
    private String updatedUsername;
    private String updatedPassword;
}
