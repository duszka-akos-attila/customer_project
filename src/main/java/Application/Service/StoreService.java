package Application.Service;

import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStaffException;
import Application.Exception.UnknownStoreException;
import Application.Model.Store;

import java.util.Collection;

public interface StoreService {

    Collection<Store> getAllStores();

    void recordStore(Store store) throws UnknownStaffException, UnknownAddressException;

    void updateFirstMatch(Store originalStore, Store updatedStore) throws UnknownAddressException, UnknownStoreException, UnknownStaffException;

    void deleteStore(Store store) throws UnknownAddressException, UnknownStoreException, UnknownStaffException;
}
