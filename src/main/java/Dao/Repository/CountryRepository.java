package Dao.Repository;

import Dao.Entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CountryRepository extends CrudRepository<CountryEntity, Integer> {
    Collection<CountryEntity> findByName(String name);
}