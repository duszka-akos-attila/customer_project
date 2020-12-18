package Application.Dao;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.CityEntity;
import Application.Dao.Entity.CountryEntity;
import Application.Dao.Entity.StoreEntity;
import Application.Dao.Repository.AddressRepository;
import Application.Dao.Repository.CustomerRepository;
import Application.Exception.UnknownCityException;
import Application.Exception.UnknownCountryException;
import Application.Exception.UnknownStoreException;
import Application.Model.Address;
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
class AddressDaoImplementationTest {

    @Spy
    @InjectMocks
    AddressDaoImplementation addressDaoImplementation;

    @Mock
    AddressRepository addressRepository;


    @Test
    void readAll() {

        addressDaoImplementation.readAll();

        verify(addressRepository, times(1)).findAll();

    }

    @Test
    void createAddressSuccessfully() throws UnknownCityException {

        doReturn(CityEntity.builder().city("city").build())
                .when(addressDaoImplementation).queryCity(any(),any());

        addressDaoImplementation.createAddress(getAddress());

        verify(addressRepository,times(1)).save(any());
    }

    @Test
    void createAddressWithUnknownCity() throws UnknownCityException {

        doThrow(UnknownCityException.class).when(addressDaoImplementation).queryCity(any(),any());

        assertThrows(UnknownCityException.class, ()->{
            addressDaoImplementation.createAddress(getAddress());
        });

    }

    private Address getAddress(){
        return new Address(
          "address",
          "address2",
          "district",
          "city",
          "country",
          "postalCode",
          "phone"
        );
    }
}