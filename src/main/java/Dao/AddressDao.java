package Dao;

import Model.Address;
import Exception.UnknownCityException;
import Exception.UnknownAddressException;

import java.util.Collection;

public interface AddressDao {

    Collection<Address> readAll();

    void createAddress(Address address) throws UnknownCityException;

    void updateFirstMatch(Address address, Address updatedAddress) throws UnknownAddressException, UnknownCityException;

    void deleteAddress(Address address) throws UnknownAddressException, UnknownCityException;
}
