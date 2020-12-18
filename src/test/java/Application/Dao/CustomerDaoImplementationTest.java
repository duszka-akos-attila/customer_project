package Application.Dao;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.StoreEntity;
import Application.Dao.Repository.CustomerRepository;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStoreException;
import Application.Model.Customer;
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
class CustomerDaoImplementationTest {

    @Spy
    @InjectMocks
    CustomerDaoImplementation customerDaoImplementation;

    @Mock
    CustomerRepository customerRepository;

    @Test
    void readAll() {

        customerDaoImplementation.readAll();

        verify(customerRepository, times(1)).findAll();

    }

    @Test
    void createCustomerSuccessfully() throws UnknownStoreException, UnknownAddressException {

        doReturn(StoreEntity.builder().addressEntity(new AddressEntity()).build())
                .when(customerDaoImplementation).queryStore(any());

        doReturn(AddressEntity.builder().address("address").build())
                .when(customerDaoImplementation).queryAddress(any());

        customerDaoImplementation.createCustomer(getCustomer());

        verify(customerRepository,times(1)).save(any());
    }

    @Test
    void createCustomerWithUnknownStore() throws UnknownStoreException, UnknownAddressException {

        doThrow(UnknownStoreException.class).when(customerDaoImplementation).queryStore(any());

        assertThrows(UnknownStoreException.class, ()->{
            customerDaoImplementation.createCustomer(getCustomer());
        });

    }

    @Test
    void createCustomerWithUnknownAddress() throws UnknownStoreException, UnknownAddressException {

        doReturn(StoreEntity.builder().addressEntity(new AddressEntity()).build())
                .when(customerDaoImplementation).queryStore(any());

        doThrow(UnknownAddressException.class).when(customerDaoImplementation).queryAddress(any());

        assertThrows(UnknownAddressException.class, ()->{
            customerDaoImplementation.createCustomer(getCustomer());
        });
    }

    private Customer getCustomer(){
        return new Customer(
                "storeAddress",
                "firstName",
                "lastName",
                "email",
                "address",
                1
        );
    }
}