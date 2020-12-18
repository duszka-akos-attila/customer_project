package Application.Dao.Repository;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.CityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {

    Collection<AddressEntity> findByAddressAndAddress2AndDistrictAndCityEntityAndPostalCodeAndPhone(
            String address, String address2, String district, CityEntity cityEntity, String postalCode, String phone);

    Collection<AddressEntity> findByAddressId(int addressId);

    Collection<AddressEntity> findByAddress(String address);
}
