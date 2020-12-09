package Dao;

import Model.Store;

import java.util.Collection;

public interface StoreDao {

    Collection<Store> readAll();

    void createStore(Store store) throws Exception;

    void updateFirstMatch(Store store, Store updatedStore) throws Exception;

    void deleteStore(Store store) throws Exception;
}
