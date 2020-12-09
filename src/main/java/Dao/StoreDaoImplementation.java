package Dao;

import Dao.Entity.AddressEntity;
import Dao.Entity.StaffEntity;
import Dao.Entity.StoreEntity;
import Dao.Repository.AddressRepository;
import Dao.Repository.StaffRepository;
import Dao.Repository.StoreRepository;
import Model.Store;
import Exception.UnknownAddressException;
import Exception.UnknownStaffException;
import Exception.UnknownStoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StoreDaoImplementation implements StoreDao{

    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final StaffRepository staffRepository;

    @Override
    public Collection<Store> readAll(){
        return StreamSupport.stream(storeRepository.findAll().spliterator(),false)
                .map(entity -> new Store(
                        entity.getStoreId(),
                        entity.getManagerStaffEntity().getStaffId(),
                        entity.getAddressEntity().getAddressId(),
                        entity.getLastUpdate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createStore(Store store) throws UnknownStaffException, UnknownAddressException {
        StoreEntity storeEntity;

        storeEntity = StoreEntity.builder()
                .storeId(store.getStoreId())
                .managerStaffEntity(queryStaff(store.getManagerStaffId()))
                .addressEntity(queryAddress(store.getAddressId()))
                .lastUpdate(store.getLastUpdate())
                .build();

        try{
            storeRepository.save(storeEntity);
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Store store, Store updatedStore) throws UnknownStoreException, UnknownStaffException, UnknownAddressException {
        Optional<StoreEntity> storeEntity = storeRepository.findByManagerStaffAndAddress(
                queryStaff(store.getManagerStaffId()),
                queryAddress(store.getAddressId()))
                .stream()
                .findFirst();

        if(!storeEntity.isPresent()){
            throw new UnknownStoreException(store, "Store unknown");
        }

        storeEntity.get().setManagerStaffEntity(queryStaff(updatedStore.getManagerStaffId()));
        storeEntity.get().setAddressEntity(queryAddress(updatedStore.getAddressId()));
        storeEntity.get().setLastUpdate(updatedStore.getLastUpdate());;

        try{
            storeRepository.save(storeEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }
    }

    @Override
    public void deleteStore(Store store) throws UnknownStoreException, UnknownStaffException, UnknownAddressException {
        Optional<StoreEntity> storeEntity = storeRepository.findByManagerStaffAndAddress(
                queryStaff(store.getManagerStaffId()),
                queryAddress(store.getAddressId()))
                .stream()
                .findFirst();

        if(!storeEntity.isPresent()){
            throw new UnknownStoreException(store, "Store unknown");
        }

        try{
            storeRepository.delete(storeEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR:" +e.getMessage());
        }
    }

    protected AddressEntity queryAddress(int addressId) throws UnknownAddressException {
        Optional<AddressEntity> addressEntity = addressRepository.findByAddressId(addressId).stream()
                .filter(entity -> entity.getAddressId() == (addressId))
                .findFirst();
        if( !addressEntity.isPresent()){
            throw new UnknownAddressException("Address unknown");
        }
        return addressEntity.get();
    }

    protected StaffEntity queryStaff(int staffId) throws UnknownStaffException {
        Optional<StaffEntity> staffEntity = staffRepository.findByStaffId(staffId).stream()
                .filter(entity -> entity.getStaffId() == (staffId))
                .findFirst();
        if( !staffEntity.isPresent()){
            throw new UnknownStaffException("Staff unknown");
        }
        return staffEntity.get();
    }
}
