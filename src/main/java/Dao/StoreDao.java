package Dao;

import Model.Store;
import Exception.UnknownAddressException;
import Exception.UnknownStaffException;
import Exception.UnknownStoreException;

import java.util.Collection;

public interface StoreDao {

    Collection<Store> readAll();

    void createStore(Store store) throws UnknownStaffException, UnknownAddressException;

    void updateFirstMatch(Store store, Store updatedStore) throws UnknownStoreException, UnknownStaffException, UnknownAddressException;

    void deleteStore(Store store) throws UnknownStoreException, UnknownStaffException, UnknownAddressException;
}
