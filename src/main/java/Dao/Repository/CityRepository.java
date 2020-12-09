package Dao.Repository;

import Dao.Entity.CityEntity;
import Dao.Entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CityRepository extends CrudRepository<CityEntity, Integer> {

    Collection<CityEntity> findByCityAndCountry(String city, CountryEntity countryEntity);

    Collection<CityEntity> findByCityId(int cityId);
}
