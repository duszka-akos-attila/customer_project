package Application.Dao.Repository;

import Application.Dao.Entity.CityEntity;
import Application.Dao.Entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CityRepository extends CrudRepository<CityEntity, Integer> {

    Collection<CityEntity> findByCity(String city);

    Collection<CityEntity> findByCityId(int cityId);

    Collection<CityEntity> findByCityAndCountryEntity(String city, CountryEntity countryEntity);
}
