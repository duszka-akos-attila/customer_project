package Application.Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CountryUpdateDto extends CountryDto {
    private String updatedCountry;
}
