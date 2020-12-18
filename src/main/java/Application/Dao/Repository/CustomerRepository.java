package Application.Dao.Repository;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.CustomerEntity;
import Application.Dao.Entity.StoreEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    Collection<CustomerEntity> findByStoreEntityAndFirstNameAndLastNameAndEmailAndAddressEntityAndActive(
            StoreEntity storeEntity, String fistName, String lastName, String email, AddressEntity addressEntity,
            int active);

    Collection<CustomerEntity> findByCustomerId(int customerId);
}
