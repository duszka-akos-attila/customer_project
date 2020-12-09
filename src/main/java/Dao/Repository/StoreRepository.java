package Dao.Repository;

import Dao.Entity.AddressEntity;
import Dao.Entity.StaffEntity;
import Dao.Entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface StoreRepository extends CrudRepository<StoreEntity, Integer> {
    Collection<StoreEntity> findByManagerStaffAndAddress(StaffEntity managerStaffEntity, AddressEntity addressEntity);
}
