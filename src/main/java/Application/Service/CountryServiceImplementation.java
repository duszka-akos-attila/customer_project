package Application.Service;

import Application.Dao.CountryDao;
import Application.Exception.UnknownCountryException;
import Application.Model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CountryServiceImplementation implements CountryService {

    private final CountryDao countryDao;

    @Override
    public Collection<Country> getAllCountries() {
        return countryDao.readAll();
    }

    @Override
    public void recordCountry(Country country) {
        countryDao.createCountry(country);
    }

    @Override
    public void updateFirstMatch(Country originalCountry, Country updatedCountry) throws UnknownCountryException {
        countryDao.updateFirstMatch(originalCountry, updatedCountry);
    }

    @Override
    public void deleteCountry(Country country) throws UnknownCountryException {
        countryDao.deleteCountry(country);
    }
}
