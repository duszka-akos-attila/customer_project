package Dao.Entity;

import Dao.Entity.Address.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface StoreRepository extends CrudRepository<StoreEntity, Integer> {
    Collection<StoreEntity> findByManagerStaffAndAddress(StaffEntity managerStaffEntity, AddressEntity addressEntity);
}
