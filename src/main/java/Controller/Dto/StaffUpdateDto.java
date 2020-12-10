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
    private String firstName;
    private String lastName;
    private int addressId;
    private Blob picture;
    private String email;
    private int storeId;
    private int active;
    private String username;
    private String password;
}
