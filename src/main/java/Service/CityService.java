package Service;

import Model.City;

import java.util.Collection;

public interface CityService {

    Collection<City> getAllCities();

    void recordCity(City city);

    void updateFirstMatch(City originalCity, City updatedCity);

    void deleteCity(City city);
}
