package Application.Dao;

import Application.Exception.UnknownCityException;
import Application.Exception.UnknownCountryException;
import Application.Model.City;

import java.util.Collection;

public interface CityDao {

    Collection<City> readAll();

    void createCity(City city) throws UnknownCountryException;

    void updateFirstMatch(City city, City updatedCity) throws UnknownCityException, UnknownCountryException;

    void deleteCity(City city) throws UnknownCityException, UnknownCountryException;
}
