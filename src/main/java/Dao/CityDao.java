package Dao;

import Model.City;

import java.util.Collection;

public interface CityDao {

    Collection<City> readAll();

    void createCity(City city);

    void updateFirstMatch(City city, City updatedCity);

    void deleteCity(City city);
}
