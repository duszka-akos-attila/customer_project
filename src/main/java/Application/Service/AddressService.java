package Application.Service;

import Application.Exception.UnknownCountryException;
import Application.Model.Address;
import Application.Exception.UnknownCityException;
import Application.Exception.UnknownAddressException;

import java.util.Collection;

public interface AddressService {

    Collection<Address> getAllAddresses();

    void recordAddress(Address address) throws UnknownCityException, UnknownCityException, UnknownCountryException;

    void updateFirstMatch(Address originalAddress, Address updatedAddress) throws UnknownCityException, UnknownAddressException, UnknownCountryException;

    void deleteAddress(Address address) throws UnknownCityException, UnknownAddressException, UnknownCountryException;
}
