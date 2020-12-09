package Dao.Entity.Address;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
    Collection<AddressEntity> findByAddressAndAddress2AndDistrictAndCityAndPostalCodeAndPhone(
            String address, String address2, String district, CityEntity cityEntity, String postalCode, String phone);
}
