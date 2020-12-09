package Dao.Repository;

import Dao.Entity.AddressEntity;
import Dao.Entity.StaffEntity;
import Dao.Entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface StaffRepository extends CrudRepository<StaffEntity, Integer> {
    Collection<StaffEntity> findByFirstNameAndLastNameAndAddressAndEmailAndStoreAndActiveAndUserName(
            String firstName, String lastName, AddressEntity addressEntity, String email, StoreEntity storeEntity,
            int active, String username
    );
}
