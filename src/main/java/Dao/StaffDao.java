package Dao;

import Model.Staff;

import java.util.Collection;

public interface StaffDao {

    Collection<Staff> readAll();

    void createStaff(Staff staff) throws Exception;

    void updateFirstMatch(Staff staff, Staff updatedStaff) throws Exception;

    void deleteStaff(Staff staff) throws Exception;
}
