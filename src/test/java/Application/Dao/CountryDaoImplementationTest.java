package Application.Dao;

import Application.Dao.Repository.CountryRepository;
import Application.Dao.Repository.CustomerRepository;
import Application.Model.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CountryDaoImplementationTest {

    @Spy
    @InjectMocks
    CountryDaoImplementation countryDaoImplementation;

    @Mock
    CountryRepository countryRepository;

    @Test
    void readAll() {

        countryDaoImplementation.readAll();

        verify(countryRepository, times(1)).findAll();

    }

    @Test
    void createCountrySuccessfully() {

        countryDaoImplementation.createCountry(getCountry());

        verify(countryRepository,times(1)).save(any());

    }

    private Country getCountry(){
        return new Country(
                "country"
        );
    }
}