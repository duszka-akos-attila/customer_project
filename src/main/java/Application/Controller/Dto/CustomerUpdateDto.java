package Application.Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateDto extends CustomerDto {
    private String updatedStoreAddress;
    private String updatedFirstName;
    private String updatedLastName;
    private String updatedEmail;
    private String updatedAddress;
    private int updatedActive;
}
