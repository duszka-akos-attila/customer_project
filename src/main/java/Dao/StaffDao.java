package Dao;

import Model.Staff;

import java.util.Collection;

public interface StaffDao {

    Collection<Staff> readAll();

    void createStaff(Staff staff);

    void updateFirstMatch(Staff staff, Staff updatedStaff);

    void deleteStaff(Staff staff);
}
