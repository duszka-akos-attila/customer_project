package Dao.Entity;

import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Collection;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    Collection<CustomerEntity> findByStoreAndFirstNameAndLastNameAndEmailAndAddressAndActiveAndCreateDate(
            StoreEntity storeEntity, String fistName, String lastName, String email, AddressEntity addressEntity,
            int active, Timestamp createDate
    );
}
