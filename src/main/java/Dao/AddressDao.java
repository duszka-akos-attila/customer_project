package Dao;

import Model.Address;

import java.util.Collection;

public interface AddressDao {

    Collection<Address> readAll();

    void createAddress(Address address);

    void updateFirstMatch(Address address, Address updatedAddress);

    void deleteAddress(Address address);
}
