package Dao;

import Model.City;

import java.util.Collection;

public interface CityDao {

    Collection<City> readAll();

    void createCity(City city) throws Exception;

    void updateFirstMatch(City city, City updatedCity) throws Exception;

    void deleteCity(City city) throws Exception;
}
