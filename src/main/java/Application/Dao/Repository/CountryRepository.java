package Application.Dao.Repository;

import Application.Dao.Entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {

    Optional<CountryEntity> findByCountry(String country);

    Collection<CountryEntity> findByCountryId(int countryId);
}