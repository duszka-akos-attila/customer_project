package Application.Model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class City {
    private String city;
    private String Country;
}
