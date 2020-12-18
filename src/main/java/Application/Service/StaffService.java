package Application.Service;

import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStaffException;
import Application.Exception.UnknownStoreException;
import Application.Model.Staff;

import java.util.Collection;

public interface StaffService {

    Collection<Staff> getAllStaffs();

    void recordStaff(Staff staff) throws UnknownAddressException, UnknownStoreException;

    void updateFirstMatch(Staff originalStaff, Staff updatedStaff) throws UnknownAddressException, UnknownStaffException, UnknownStoreException;

    void deleteStaff(Staff staff) throws UnknownAddressException, UnknownStaffException, UnknownStoreException;
}
