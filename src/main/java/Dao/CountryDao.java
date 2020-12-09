package Dao;

import Model.Country;
import Exception.UnknownCountryException;

import java.util.Collection;

public interface CountryDao {

    Collection<Country> readAll();

    void createCountry(Country country);

    void updateFirstMatch(Country country, Country updatedCountry) throws UnknownCountryException;

    void deleteCountry(Country country) throws UnknownCountryException;
}
