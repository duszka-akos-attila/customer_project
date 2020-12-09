package Dao;

import Model.Country;

import java.util.Collection;

public interface CountryDao {

    Collection<Country> readAll();

    void createCountry(Country country);

    void updateFirstMatch(Country country, Country updatedCountry);

    void deleteCountry(Country country);
}
