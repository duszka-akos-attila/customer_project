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
public class Store {
    private String managerFirstName;
    private String managerLastName;
    private String address;
}
