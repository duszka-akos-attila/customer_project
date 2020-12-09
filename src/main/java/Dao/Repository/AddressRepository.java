package Dao.Repository;

import Dao.Entity.AddressEntity;
import Dao.Entity.CityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

    Collection<AddressEntity> findByAddressAndAddress2AndDistrictAndCityAndPostalCodeAndPhone(
            String address, String address2, String district, CityEntity cityEntity, String postalCode, String phone);

    Collection<AddressEntity> findByAddressId(int addressId);
}
