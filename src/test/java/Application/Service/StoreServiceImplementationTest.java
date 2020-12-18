package Application.Service;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.StaffEntity;
import Application.Dao.Entity.StoreEntity;
import Application.Dao.StoreDao;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStaffException;
import Application.Exception.UnknownStoreException;
import Application.Model.Store;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplementationTest {

    @InjectMocks
    private StoreServiceImplementation storeServiceImplementation;
    @Mock
    private StoreDao storeDao;

    @Test
    void recordStoreSuccessfully() throws UnknownStaffException, UnknownAddressException {

        storeServiceImplementation.recordStore(getStore());

        verify(storeDao, times(1)).createStore(getStore());
    }

    @Test
    void recordStoreWithUnknownStaff() throws UnknownStaffException, UnknownAddressException {

        doThrow(UnknownStaffException.class).when(storeDao).createStore(any());

        assertThrows(UnknownStaffException.class, ()->{
            storeServiceImplementation.recordStore(getStore());
        });
    }

    @Test
    void recordStoreWithUnknownAddress() throws UnknownStaffException, UnknownAddressException {

        doThrow(UnknownAddressException.class).when(storeDao).createStore(any());

        assertThrows(UnknownAddressException.class, ()->{
            storeServiceImplementation.recordStore(getStore());
        });
    }

    @Test
    void getAllStores() {
        when(storeDao.readAll()).thenReturn(getDefaultStores());
        Collection<Store> actual = storeServiceImplementation.getAllStores();

        assertThat(getDefaultStores(), is(actual));
    }

    @Test
    void updateFirstMatchSuccessfully() throws UnknownAddressException, UnknownStoreException, UnknownStaffException {

        storeServiceImplementation.updateFirstMatch(getStore(), getUpdatedStore());

        verify(storeDao, times(1)).updateFirstMatch(getStore(), getUpdatedStore() );
    }

    @Test
    void updateFirstMatchWithUnknownAddress() throws UnknownAddressException, UnknownStoreException, UnknownStaffException {

        doThrow(UnknownAddressException.class).when(storeDao).updateFirstMatch(any(), any());

        assertThrows(UnknownAddressException.class, ()->{
            storeServiceImplementation.updateFirstMatch(getStore(), getUpdatedStore());
        });

    }

    @Test
    void updateFirstMatchWithUnknownStore() throws UnknownAddressException, UnknownStoreException, UnknownStaffException {

        doThrow(UnknownStoreException.class).when(storeDao).updateFirstMatch(any(), any());

        assertThrows(UnknownStoreException.class, ()->{
            storeServiceImplementation.updateFirstMatch(getStore(), getUpdatedStore());
        });

    }

    @Test
    void updateFirstMatchWithUnknownStaff() throws UnknownAddressException, UnknownStoreException, UnknownStaffException {

        doThrow(UnknownStaffException.class).when(storeDao).updateFirstMatch(any(), any());

        assertThrows(UnknownStaffException.class, ()->{
            storeServiceImplementation.updateFirstMatch(getStore(), getUpdatedStore());
        });

    }

    @Test
    void deleteStoreSuccessfully() throws UnknownAddressException, UnknownStoreException, UnknownStaffException {

        storeServiceImplementation.deleteStore(getStore());

        verify(storeDao, times(1)).deleteStore(getStore());

    }

    @Test
    void deleteStoreWithUnknownAddress() throws UnknownAddressException, UnknownStoreException, UnknownStaffException {

        doThrow(UnknownAddressException.class).when(storeDao).deleteStore(any());

        assertThrows(UnknownAddressException.class, ()->{
            storeServiceImplementation.deleteStore(getStore());
        });

    }

    @Test
    void deleteStoreWithUnknownStore() throws UnknownAddressException, UnknownStoreException, UnknownStaffException {

        doThrow(UnknownStoreException.class).when(storeDao).deleteStore(any());

        assertThrows(UnknownStoreException.class, ()->{
            storeServiceImplementation.deleteStore(getStore());
        });

    }

    @Test
    void deleteStoreWithUnknownStaff() throws UnknownAddressException, UnknownStoreException, UnknownStaffException {

        doThrow(UnknownStaffException.class).when(storeDao).deleteStore(any());

        assertThrows(UnknownStaffException.class, ()->{
            storeServiceImplementation.deleteStore(getStore());
        });

    }

    private Store getStore() {
        return new Store(
                "managerFirstName",
                "managerLastName",
                "address"
        );
    }

    private Store getUpdatedStore() {
        return new Store(
                "managerFirstName2",
                "managerLastName2",
                "address2"
        );
    }

    private Collection<Store> getDefaultStores() {
        return Arrays.asList(
                new Store(
                        "managerFirstName",
                        "managerLastName",
                        "address"
                ),
                new Store(
                        "managerFirstName2",
                        "managerLastName2",
                        "address2"
                ),
                new Store(
                        "managerFirstName2",
                        "managerLastName2",
                        "address2"
                ));
    }
}