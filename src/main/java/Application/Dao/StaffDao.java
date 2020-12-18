package Application.Dao;

import Application.Model.Staff;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStaffException;
import Application.Exception.UnknownStoreException;

import java.util.Collection;

public interface StaffDao {

    Collection<Staff> readAll();

    void createStaff(Staff staff) throws UnknownAddressException, UnknownStoreException;

    void updateFirstMatch(Staff staff, Staff updatedStaff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException;

    void deleteStaff(Staff staff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException;
}
