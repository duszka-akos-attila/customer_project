package Service;

import Model.Staff;

import java.util.Collection;

public interface StaffService {

    Collection<Staff> getAllStaffs();

    void recordStaff(Staff staff);

    void updateFirstMatch(Staff originalStaff, Staff updatedStaff);

    void deleteStaff(Staff staff);
}
