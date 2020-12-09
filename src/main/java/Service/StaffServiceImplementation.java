package Service;

import Dao.StaffDao;
import Model.Staff;
import Exception.UnknownAddressException;
import Exception.UnknownStoreException;
import Exception.UnknownStaffException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class StaffServiceImplementation implements StaffService{

    private final StaffDao staffDao;

    @Override
    public Collection<Staff> getAllStaffs() {
        return staffDao.readAll();
    }

    @Override
    public void recordStaff(Staff staff) throws UnknownAddressException, UnknownStoreException {
        staffDao.createStaff(staff);
    }

    @Override
    public void updateFirstMatch(Staff originalStaff, Staff updatedStaff) throws UnknownAddressException, UnknownStaffException, UnknownStoreException {
        staffDao.updateFirstMatch(originalStaff, updatedStaff);
    }

    @Override
    public void deleteStaff(Staff staff) throws UnknownAddressException, UnknownStaffException, UnknownStoreException {
        staffDao.deleteStaff(staff);
    }
}
