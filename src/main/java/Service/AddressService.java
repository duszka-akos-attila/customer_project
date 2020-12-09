package Service;

import Model.Address;
import Exception.UnknownCityException;
import Exception.UnknownAddressException;

import java.util.Collection;

public interface AddressService {

    Collection<Address> getAllAddresses();

    void recordAddress(Address address) throws UnknownCityException, UnknownCityException;

    void updateFirstMatch(Address originalAddress, Address updatedAddress) throws UnknownCityException, UnknownAddressException;

    void deleteAddress(Address address) throws UnknownCityException, UnknownAddressException;
}
