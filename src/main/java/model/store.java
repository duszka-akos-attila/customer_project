package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class store {
    private int storeId;
    private int managerStaffId;
    private int addressId;
    private Date lastUpdate;
}
