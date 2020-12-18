package Application.Dao;

import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStaffException;
import Application.Exception.UnknownStoreException;
import Application.Model.Store;

import java.util.Collection;

public interface StoreDao {

    Collection<Store> readAll();

    void createStore(Store store) throws UnknownStaffException, UnknownAddressException;

    void updateFirstMatch(Store store, Store updatedStore) throws UnknownStoreException, UnknownStaffException, UnknownAddressException;

    void deleteStore(Store store) throws UnknownStoreException, UnknownStaffException, UnknownAddressException;
}
