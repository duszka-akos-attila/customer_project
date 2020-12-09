package Service;

import Model.Address;

import java.util.Collection;

public interface AddressService {

    Collection<Address> getAllAddresses();

    void recordAddress(Address address);

    void updateFirstMatch(Address originalAddress, Address updatedAddress);

    void deleteAddress(Address address);
}
