package Service;

import Model.Staff;
import Exception.UnknownAddressException;
import Exception.UnknownStoreException;
import Exception.UnknownStaffException;

import java.util.Collection;

public interface StaffService {

    Collection<Staff> getAllStaffs();

    void recordStaff(Staff staff) throws UnknownAddressException, UnknownStoreException;

    void updateFirstMatch(Staff originalStaff, Staff updatedStaff) throws UnknownAddressException, UnknownStaffException, UnknownStoreException;

    void deleteStaff(Staff staff) throws UnknownAddressException, UnknownStaffException, UnknownStoreException;
}
