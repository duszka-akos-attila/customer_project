package Application.Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Blob;
import java.sql.Timestamp;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {
    private String firstName;
    private String lastName;
    private String address;
    //private Blob picture;
    private String email;
    private String storeAddress;
    private int active;
    private String username;
    private String password;
}
