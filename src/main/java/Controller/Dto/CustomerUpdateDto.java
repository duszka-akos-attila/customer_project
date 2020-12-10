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
    private int storeId;
    private String firstName;
    private String lastName;
    private String email;
    private int addressId;
    private int active;
}
