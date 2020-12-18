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
import Application.Model.Staff;
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
public class StaffDaoImplementation implements StaffDao {

    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final StaffRepository staffRepository;

    @Override
    public Collection<Staff> readAll() {
        return StreamSupport.stream(staffRepository.findAll().spliterator(), false)
                .map(entity -> new Staff(
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getAddressEntity().getAddress(),
                        //entity.getPicture(),
                        entity.getEmail(),
                        entity.getStoreEntity().getAddressEntity().getAddress(),
                        entity.getActive(),
                        entity.getUsername(),
                        entity.getPassword()
                ))
                .collect(Collectors.toList());

    }

    @Override
    public void createStaff(Staff staff) throws UnknownAddressException, UnknownStoreException {
        StaffEntity staffEntity;

        staffEntity = StaffEntity.builder()
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .addressEntity(queryAddress(staff.getAddress()))
                //.picture(staff.getPicture())
                .email(staff.getEmail())
                .storeEntity(queryStore(staff.getStoreAddress()))
                .active(staff.getActive())
                .username(staff.getUsername())
                .password(staff.getPassword())
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();

        try {
            staffRepository.save(staffEntity);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Staff staff, Staff updatedStaff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException {
        Optional<StaffEntity> staffEntity = staffRepository.findByFirstNameAndLastNameAndAddressEntityAndEmailAndStoreEntityAndActiveAndUsername(
                staff.getFirstName(),
                staff.getLastName(),
                queryAddress(staff.getAddress()),
                staff.getEmail(),
                queryStore(staff.getStoreAddress()),
                staff.getActive(),
                staff.getUsername())
                .stream()
                .findFirst();

        if (!staffEntity.isPresent()) {
            throw new UnknownStaffException(staff, "Staff unknown");
        }

        staffEntity.get().setFirstName(updatedStaff.getFirstName());
        staffEntity.get().setLastName(updatedStaff.getLastName());
        staffEntity.get().setAddressEntity(queryAddress(updatedStaff.getAddress()));
        //staffEntity.get().setPicture(updatedStaff.getPicture());
        staffEntity.get().setEmail(updatedStaff.getEmail());
        staffEntity.get().setStoreEntity(queryStore(updatedStaff.getStoreAddress()));
        staffEntity.get().setActive(updatedStaff.getActive());
        staffEntity.get().setUsername(updatedStaff.getUsername());
        staffEntity.get().setPassword(updatedStaff.getPassword());
        staffEntity.get().setLastUpdate(new Timestamp((new Date()).getTime()));

        try {
            staffRepository.save(staffEntity.get());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void deleteStaff(Staff staff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException {
        Optional<StaffEntity> staffEntity = staffRepository.findByFirstNameAndLastNameAndAddressEntityAndEmailAndStoreEntityAndActiveAndUsername(
                staff.getFirstName(),
                staff.getLastName(),
                queryAddress(staff.getAddress()),
                staff.getEmail(),
                queryStore(staff.getStoreAddress()),
                staff.getActive(),
                staff.getUsername())
                .stream()
                .findFirst();
        if (!staffEntity.isPresent()) {
            throw new UnknownStaffException(staff, "Staff unknown");
        }

        try {
            staffRepository.delete(staffEntity.get());
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

    protected StoreEntity queryStore(String address) throws UnknownStoreException {
        Optional<StoreEntity> storeEntity = storeRepository.findByAddressEntity_Address(address).stream()
                .filter(entity -> entity.getAddressEntity().getAddress().equals(address))
                .findFirst();
        if (!storeEntity.isPresent()) {
            throw new UnknownStoreException("Store unknown");
        }
        return storeEntity.get();
    }
}
