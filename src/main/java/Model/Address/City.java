package Model.Address;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class City {
    private int cityId;
    private String city;
    private int countryId;
    private Date lastUpdate;
}
