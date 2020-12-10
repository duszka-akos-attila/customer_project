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
public class CustomerDto {
    private int storeId;
    private String firstName;
    private String lastName;
    private String email;
    private int addressId;
    private int active;
    private Timestamp createDate;
    private Timestamp lastUpdate;
}
