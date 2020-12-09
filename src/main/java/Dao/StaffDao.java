package Dao;

import Model.Staff;
import Exception.UnknownAddressException;
import Exception.UnknownStaffException;
import Exception.UnknownStoreException;

import java.util.Collection;

public interface StaffDao {

    Collection<Staff> readAll();

    void createStaff(Staff staff) throws UnknownAddressException, UnknownStoreException;

    void updateFirstMatch(Staff staff, Staff updatedStaff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException;

    void deleteStaff(Staff staff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException;
}
