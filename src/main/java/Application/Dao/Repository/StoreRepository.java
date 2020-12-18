package Application.Dao.Repository;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.StaffEntity;
import Application.Dao.Entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface StoreRepository extends CrudRepository<StoreEntity, Integer> {

    Collection<StoreEntity> findByManagerStaffEntityAndAddressEntity(StaffEntity managerStaffEntity, AddressEntity addressEntity);

    Collection<StoreEntity> findByStoreId(int storeId);

    Collection<StoreEntity> findByAddressEntity_Address(String address);
}
