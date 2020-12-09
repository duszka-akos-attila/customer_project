package Dao.Entity.Address;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {
    Collection<CountryEntity> findByName(String name);
}