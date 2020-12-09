package Service;

import Model.City;
import Exception.UnknownCountryException;
import Exception.UnknownCityException;

import java.util.Collection;

public interface CityService {

    Collection<City> getAllCities();

    void recordCity(City city) throws UnknownCountryException;

    void updateFirstMatch(City originalCity, City updatedCity) throws UnknownCityException, UnknownCountryException;

    void deleteCity(City city) throws UnknownCityException, UnknownCountryException;
}
