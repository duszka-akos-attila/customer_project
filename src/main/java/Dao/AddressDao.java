package Dao;

import Model.Address;

import java.util.Collection;

public interface AddressDao {

    Collection<Address> readAll();

    void createAddress(Address address) throws Exception;

    void updateFirstMatch(Address address, Address updatedAddress) throws Exception;

    void deleteAddress(Address address) throws Exception;
}
