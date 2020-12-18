package Application.Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String storeAddress;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private int active;
}
