package Service;

import Model.Country;

import java.util.Collection;

public interface CountryService {

    Collection<Country> getAllCountries();

    void recordCountry(Country country);

    void updateFirstMatch(Country originalCountry, Country updatedCountry);

    void deleteCountry(Country country);
}
