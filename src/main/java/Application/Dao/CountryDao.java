package Application.Dao;

import Application.Model.Country;
import Application.Exception.UnknownCountryException;

import java.util.Collection;

public interface CountryDao {

    Collection<Country> readAll();

    void createCountry(Country country);

    void updateFirstMatch(Country country, Country updatedCountry) throws UnknownCountryException;

    void deleteCountry(Country country) throws UnknownCountryException;
}
