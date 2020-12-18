package Application.Dao;

import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownCityException;
import Application.Exception.UnknownCountryException;
import Application.Model.Address;

import java.util.Collection;

public interface AddressDao {

    Collection<Address> readAll();

    void createAddress(Address address) throws UnknownCityException;

    void updateFirstMatch(Address address, Address updatedAddress) throws UnknownAddressException, UnknownCountryException, UnknownCityException;

    void deleteAddress(Address address) throws UnknownAddressException, UnknownCityException;
}
