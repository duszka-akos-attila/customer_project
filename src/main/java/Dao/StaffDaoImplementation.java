package Dao;

import Dao.Entity.AddressEntity;
import Dao.Entity.StaffEntity;
import Dao.Entity.StoreEntity;
import Dao.Repository.AddressRepository;
import Dao.Repository.StaffRepository;
import Dao.Repository.StoreRepository;
import Model.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StaffDaoImplementation implements StaffDao{

    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final StaffRepository staffRepository;

    @Override
    public Collection<Staff> readAll() {
        return StreamSupport.stream(staffRepository.findAll().spliterator(),false)
                .map(entity -> new Staff(
                        entity.getStaffId(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getAddressEntity().getAddressId(),
                        entity.getPicture(),
                        entity.getEmail(),
                        entity.getStoreEntity().getStoreId(),
                        entity.getActive(),
                        entity.getUsername(),
                        entity.getPassword(),
                        entity.getLastUpdate()
                ))
                .collect(Collectors.toList());

    }

    @Override
    public void createStaff(Staff staff) throws Exception {
        StaffEntity staffEntity;

        staffEntity = StaffEntity.builder()
                .staffId(staff.getStaffId())
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .addressEntity(queryAddress(staff.getAddressId()))
                .picture(staff.getPicture())
                .email(staff.getEmail())
                .storeEntity(queryStore(staff.getStoreId()))
                .active(staff.getActive())
                .username(staff.getUsername())
                .password(staff.getPassword())
                .lastUpdate(staff.getLastUpdate())
                .build();

        try{
            staffRepository.save(staffEntity);
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Staff staff, Staff updatedStaff) throws Exception {
        Optional<StaffEntity> staffEntity = staffRepository.findByFirstNameAndLastNameAndAddressAndEmailAndStoreAndActiveAndUserName(
                staff.getFirstName(),
                staff.getLastName(),
                queryAddress(staff.getAddressId()),
                staff.getEmail(),
                queryStore(staff.getStoreId()),
                staff.getActive(),
                staff.getUsername())
                .stream()
                .findFirst();

        if(!staffEntity.isPresent()){
            throw new Exception("Unknown Staff : "+ staff.toString());
        }

        staffEntity.get().setFirstName(updatedStaff.getFirstName());
        staffEntity.get().setLastName(updatedStaff.getLastName());
        staffEntity.get().setAddressEntity(queryAddress(updatedStaff.getAddressId()));
        staffEntity.get().setPicture(updatedStaff.getPicture());
        staffEntity.get().setEmail(updatedStaff.getEmail());
        staffEntity.get().setStoreEntity(queryStore(updatedStaff.getStoreId()));
        staffEntity.get().setActive(updatedStaff.getActive());
        staffEntity.get().setUsername(updatedStaff.getUsername());
        staffEntity.get().setPassword(updatedStaff.getPassword());
        staffEntity.get().setLastUpdate(updatedStaff.getLastUpdate());

        try{
            staffRepository.save(staffEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }
    }

    @Override
    public void deleteStaff(Staff staff) throws Exception {
        Optional<StaffEntity> staffEntity = staffRepository.findByFirstNameAndLastNameAndAddressAndEmailAndStoreAndActiveAndUserName(
                staff.getFirstName(),
                staff.getLastName(),
                queryAddress(staff.getAddressId()),
                staff.getEmail(),
                queryStore(staff.getStoreId()),
                staff.getActive(),
                staff.getUsername())
                .stream()
                .findFirst();
        if(!staffEntity.isPresent()){
            throw new Exception("Unknown Staff: " +staff.toString());
        }

        try{
            staffRepository.delete(staffEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR:" +e.getMessage());
        }
    }

    protected AddressEntity queryAddress(int addressId) throws Exception {
        Optional<AddressEntity> addressEntity = addressRepository.findByAddressId(addressId).stream()
                .filter(entity -> entity.getAddressId() == (addressId))
                .findFirst();
        if( !addressEntity.isPresent()){
            throw new Exception("Unknown address");
        }
        return addressEntity.get();
    }

    protected StoreEntity queryStore(int storeId) throws Exception {
        Optional<StoreEntity> storeEntity = storeRepository.findByStoreId(storeId).stream()
                .filter(entity -> entity.getStoreId() == (storeId))
                .findFirst();
        if( !storeEntity.isPresent()){
            throw new Exception("Unknown store");
        }
        return storeEntity.get();
    }
}
