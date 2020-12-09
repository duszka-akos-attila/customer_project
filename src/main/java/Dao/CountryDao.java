package Dao;

import Model.Country;

import java.util.Collection;

public interface CountryDao {

    Collection<Country> readAll();

    void createCountry(Country country);

    void updateFirstMatch(Country country, Country updatedCountry) throws Exception;

    void deleteCountry(Country country) throws Exception;
}
