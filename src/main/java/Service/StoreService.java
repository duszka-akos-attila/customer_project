package Service;

import Model.Store;

import java.util.Collection;

public interface StoreService {

    Collection<Store> getAllStores();

    void recordStore(Store store);

    void updateFirstMatch(Store originalStore, Store updatedStore);

    void deleteStore(Store store);
}
