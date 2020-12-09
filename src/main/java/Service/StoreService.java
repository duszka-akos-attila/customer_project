package Service;

import Model.Store;
import Exception.UnknownAddressException;
import Exception.UnknownStoreException;
import Exception.UnknownStaffException;

import java.util.Collection;

public interface StoreService {

    Collection<Store> getAllStores();

    void recordStore(Store store) throws UnknownStaffException, UnknownAddressException;

    void updateFirstMatch(Store originalStore, Store updatedStore) throws UnknownAddressException, UnknownStoreException, UnknownStaffException;

    void deleteStore(Store store) throws UnknownAddressException, UnknownStoreException, UnknownStaffException;
}
