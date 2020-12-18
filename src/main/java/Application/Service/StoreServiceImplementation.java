package Application.Service;

import Application.Dao.StoreDao;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStaffException;
import Application.Exception.UnknownStoreException;
import Application.Model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class StoreServiceImplementation implements StoreService {

    private final StoreDao storeDao;

    @Override
    public Collection<Store> getAllStores() {
        return storeDao.readAll();
    }

    @Override
    public void recordStore(Store store) throws UnknownStaffException, UnknownAddressException {
        storeDao.createStore(store);
    }

    @Override
    public void updateFirstMatch(Store originalStore, Store updatedStore) throws UnknownAddressException, UnknownStoreException, UnknownStaffException {
        storeDao.updateFirstMatch(originalStore, updatedStore);
    }

    @Override
    public void deleteStore(Store store) throws UnknownAddressException, UnknownStoreException, UnknownStaffException {
        storeDao.deleteStore(store);

    }
}
