package Application.Dao;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.StaffEntity;
import Application.Dao.Entity.StoreEntity;
import Application.Dao.Repository.StaffRepository;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStaffException;
import Application.Exception.UnknownStoreException;
import Application.Model.Address;
import Application.Model.Staff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class StaffDaoImplementationTest {

    @Spy
    @InjectMocks
    private StaffDaoImplementation staffDaoImplementation;
    @Mock
    private StaffRepository staffRepository;

    @Test
    void readAll() {

        staffDaoImplementation.readAll();

        verify(staffRepository, times(1)).findAll();
    }

    @Test
    void createStaffSuccessfully() throws UnknownAddressException, UnknownStoreException {

        doReturn(AddressEntity.builder().address("address").build())
                .when(staffDaoImplementation).queryAddress(any());

        doReturn(StoreEntity.builder().addressEntity(new AddressEntity()).build())
                .when(staffDaoImplementation).queryStore(any());

        staffDaoImplementation.createStaff(getStaff());

        verify(staffRepository,times(1)).save(any());

    }

    @Test
    public void createStaffWithUnknownAddress() throws UnknownAddressException, UnknownStoreException {

        doThrow(UnknownAddressException.class).when(staffDaoImplementation).queryAddress(any());

        assertThrows(UnknownAddressException.class, ()->{
            staffDaoImplementation.createStaff(getStaff());
        });
    }

    @Test
    public void createStaffWithUnknownStore() throws UnknownAddressException, UnknownStoreException {

        doReturn(AddressEntity.builder().address("address").build())
                .when(staffDaoImplementation).queryAddress(any());

        doThrow(UnknownStoreException.class).when(staffDaoImplementation).queryStore(any());

        assertThrows(UnknownStoreException.class, ()->{
            staffDaoImplementation.createStaff(getStaff());
        });
    }

    private Staff getStaff(){
        return new Staff(
                "firstname",
                "lastname",
                "address",
                "email",
                "storeAddress",
                1,
                "username",
                "password"
        );
    }
}