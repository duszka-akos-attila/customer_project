package Application.Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StoreUpdateDto extends StoreDto{
    private String updatedManagerFirstName;
    private String updatedManagerLastName;
    private String updatedAddress;
}
