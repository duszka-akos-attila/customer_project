package Application.Service;

import Application.Dao.CityDao;
import Application.Model.City;
import Application.Exception.UnknownCountryException;
import Application.Exception.UnknownCityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CityServiceImplementation implements CityService{

    private final CityDao cityDao;

    @Override
    public Collection<City> getAllCities() {
        return cityDao.readAll();
    }

    @Override
    public void recordCity(City city) throws UnknownCountryException {
        cityDao.createCity(city);
    }

    @Override
    public void updateFirstMatch(City originalCity, City updatedCity) throws UnknownCityException, UnknownCountryException {
        cityDao.updateFirstMatch(originalCity, updatedCity);
    }

    @Override
    public void deleteCity(City city) throws UnknownCityException, UnknownCountryException {
        cityDao.deleteCity(city);

    }
}
