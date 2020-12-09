package Dao;

import Model.Store;

import java.util.Collection;

public interface StoreDao {

    Collection<Store> readAll();

    void createStore(Store store);

    void updateFirstMatch(Store store, Store updatedStore);

    void deleteStore(Store store);
}
