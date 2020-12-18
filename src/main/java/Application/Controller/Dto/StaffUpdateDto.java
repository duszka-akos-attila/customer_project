package Application.Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Blob;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StaffUpdateDto extends StaffDto{
    private String updatedFirstName;
    private String updatedLastName;
    private String updatedAddress;
    //private Blob updatedPicture;
    private String updatedEmail;
    private String updatedStoreAddress;
    private int updatedActive;
    private String updatedUsername;
    private String updatedPassword;
}
