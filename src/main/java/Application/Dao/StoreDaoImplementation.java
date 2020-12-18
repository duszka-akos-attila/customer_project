package Application.Dao;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.StaffEntity;
import Application.Dao.Entity.StoreEntity;
import Application.Dao.Repository.AddressRepository;
import Application.Dao.Repository.StaffRepository;
import Application.Dao.Repository.StoreRepository;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStaffException;
import Application.Exception.UnknownStoreException;
import Application.Model.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StoreDaoImplementation implements StoreDao {

    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final StaffRepository staffRepository;

    @Override
    public Collection<Store> readAll() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(), false)
                .map(entity -> new Store(
                        entity.getManagerStaffEntity().getFirstName(),
                        entity.getManagerStaffEntity().getLastName(),
                        entity.getAddressEntity().getAddress()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createStore(Store store) throws UnknownStaffException, UnknownAddressException {
        StoreEntity storeEntity;

        storeEntity = StoreEntity.builder()
                .managerStaffEntity(queryStaff(store.getManagerFirstName(), store.getManagerLastName()))
                .addressEntity(queryAddress(store.getAddress()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();

        try {
            storeRepository.save(storeEntity);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Store store, Store updatedStore) throws UnknownStoreException, UnknownStaffException, UnknownAddressException {
        Optional<StoreEntity> storeEntity = storeRepository.findByAddressEntity_Address(
                store.getAddress())
                .stream()
                .findFirst();

        if (!storeEntity.isPresent()) {
            throw new UnknownStoreException(store, "Store unknown");
        }

        storeEntity.get().setManagerStaffEntity(queryStaff(updatedStore.getManagerFirstName(), updatedStore.getManagerLastName()));
        storeEntity.get().setAddressEntity(queryAddress(updatedStore.getAddress()));
        storeEntity.get().setLastUpdate(new Timestamp((new Date()).getTime()));

        try {
            storeRepository.save(storeEntity.get());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void deleteStore(Store store) throws UnknownStoreException {
        Optional<StoreEntity> storeEntity = storeRepository.findByAddressEntity_Address(
                store.getAddress())
                .stream()
                .findFirst();

        if (!storeEntity.isPresent()) {
            throw new UnknownStoreException(store, "Store unknown");
        }

        try {
            storeRepository.delete(storeEntity.get());
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
        }
    }

    protected AddressEntity queryAddress(String address) throws UnknownAddressException {
        Optional<AddressEntity> addressEntity = addressRepository.findByAddress(address).stream()
                .filter(entity -> entity.getAddress().equals(address))
                .findFirst();
        if (!addressEntity.isPresent()) {
            throw new UnknownAddressException("Address unknown");
        }
        return addressEntity.get();
    }

    protected StaffEntity queryStaff(String firstName, String lastName) throws UnknownStaffException {
        Optional<StaffEntity> staffEntity = staffRepository.findByFirstNameAndLastName(firstName, lastName).stream()
                .filter(entity -> entity.getFirstName().equals(firstName))
                .filter(entity -> entity.getLastName().equals(lastName))
                .findFirst();
        if (!staffEntity.isPresent()) {
            throw new UnknownStaffException("Staff unknown");
        }
        return staffEntity.get();
    }
}
