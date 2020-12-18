package Application.Dao;

import Application.Exception.UnknownCountryException;
import Application.Model.Country;

import java.util.Collection;

public interface CountryDao {

    Collection<Country> readAll();

    void createCountry(Country country);

    void updateFirstMatch(Country country, Country updatedCountry) throws UnknownCountryException;

    void deleteCountry(Country country) throws UnknownCountryException;
}
