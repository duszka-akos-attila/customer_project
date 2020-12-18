package Application.Dao;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.StaffEntity;
import Application.Dao.Repository.StoreRepository;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStaffException;
import Application.Model.Store;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StoreDaoImplementationTest {

    @Spy
    @InjectMocks
    private StoreDaoImplementation storeDaoImplementation;
    @Mock
    private StoreRepository storeRepository;

    @Test
    void readAll() {
        storeDaoImplementation.readAll();

        verify(storeRepository, times(1)).findAll();
    }

    @Test
    void createStoreSuccessfully() throws UnknownStaffException, UnknownAddressException {
        doReturn(AddressEntity.builder().address("address").build())
                .when(storeDaoImplementation).queryAddress(any());

        doReturn(StaffEntity.builder().firstName("firstname").lastName("lastname").build())
                .when(storeDaoImplementation).queryStaff(any(),any());

        storeDaoImplementation.createStore(getStore());

        verify(storeRepository,times(1)).save(any());
    }

    @Test
    public void createStoreWithUnknownStaff() throws UnknownStaffException, UnknownAddressException {

        doThrow(UnknownStaffException.class).when(storeDaoImplementation).queryStaff(any(),any());

        assertThrows(UnknownStaffException.class, ()->{
            storeDaoImplementation.createStore(getStore());
        });
    }

    @Test
    public void createStoreWithUnknownAddress() throws UnknownStaffException, UnknownAddressException {

        doReturn(StaffEntity.builder().firstName("firstname").lastName("lastname").build())
                .when(storeDaoImplementation).queryStaff(any(),any());

        doThrow(UnknownAddressException.class).when(storeDaoImplementation).queryAddress(any());

        assertThrows(UnknownAddressException.class, ()->{
            storeDaoImplementation.createStore(getStore());
        });
    }

    private Store getStore() {
        return new Store(
                "firstname",
                "lastname",
                "address"
        );
    }
}