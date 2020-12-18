package Application.Dao.Repository;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.StaffEntity;
import Application.Dao.Entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface StaffRepository extends CrudRepository<StaffEntity, Integer> {

    Collection<StaffEntity> findByFirstNameAndLastNameAndAddressEntityAndEmailAndStoreEntityAndActiveAndUsername(
            String firstName, String lastName, AddressEntity addressEntity, String email, StoreEntity storeEntity,
            int active, String username);

    Collection<StaffEntity> findByStaffId(int staffId);

    Collection<StaffEntity> findByFirstNameAndLastName(
            String firstName, String lastName);
}
