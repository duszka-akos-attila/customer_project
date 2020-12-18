package Application.Service;

import Application.Model.Country;
import Application.Exception.UnknownCountryException;

import java.util.Collection;

public interface CountryService {

    Collection<Country> getAllCountries();

    void recordCountry(Country country);

    void updateFirstMatch(Country originalCountry, Country updatedCountry) throws UnknownCountryException;

    void deleteCountry(Country country) throws UnknownCountryException;
}
