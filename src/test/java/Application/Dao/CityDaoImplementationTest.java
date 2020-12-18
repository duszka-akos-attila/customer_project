package Application.Dao;

import Application.Dao.Entity.CountryEntity;
import Application.Dao.Repository.CityRepository;
import Application.Exception.UnknownCountryException;
import Application.Model.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityDaoImplementationTest {

    @Spy
    @InjectMocks
    CityDaoImplementation cityDaoImplementation;

    @Mock
    CityRepository cityRepository;

    @Test
    void readAll() {

        cityDaoImplementation.readAll();

        verify(cityRepository, times(1)).findAll();

    }

    @Test
    void createCitySuccessfully() throws UnknownCountryException {

        doReturn(CountryEntity.builder().country("country").build())
                .when(cityDaoImplementation).queryCountry(any());

        cityDaoImplementation.createCity(getCity());

        verify(cityRepository, times(1)).save(any());
    }

    @Test
    void createCityWithUnknownCountry() throws UnknownCountryException {

        doThrow(UnknownCountryException.class).when(cityDaoImplementation).queryCountry(any());

        assertThrows(UnknownCountryException.class, () -> {
            cityDaoImplementation.createCity(getCity());
        });
    }

    private City getCity() {
        return new City(
                "city",
                "country"
        );
    }
}