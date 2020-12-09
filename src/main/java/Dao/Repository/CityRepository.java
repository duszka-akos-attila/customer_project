package Dao.Repository;

import Dao.Entity.CityEntity;
import Dao.Entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CityRepository extends CrudRepository<CityEntity, Integer> {
    Collection<CityEntity> findByNameAndCountry(String name, CountryEntity countryEntity);
}
