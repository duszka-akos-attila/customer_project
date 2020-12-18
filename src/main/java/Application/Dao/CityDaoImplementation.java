package Application.Dao;

import Application.Dao.Entity.CityEntity;
import Application.Dao.Entity.CountryEntity;
import Application.Dao.Repository.CityRepository;
import Application.Dao.Repository.CountryRepository;
import Application.Model.City;
import Application.Exception.UnknownCityException;
import Application.Exception.UnknownCountryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CityDaoImplementation implements CityDao{

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Override
    public Collection<City> readAll() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .map(entity -> new City(
                        entity.getCity(),
                        entity.getCountryEntity().getCountry()
                )).
                collect(Collectors.toList());
    }

    @Override
    public void createCity(City city) throws UnknownCountryException {

        CityEntity cityEntity;

        cityEntity = CityEntity.builder()
                .city(city.getCity())
                .countryEntity(queryCountry(city.getCountry()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();

        try{
            cityRepository.save(cityEntity);
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }

    }

    @Override
    public void updateFirstMatch(City city, City updatedCity) throws UnknownCityException,UnknownCountryException {
        Optional<CityEntity> cityEntity = cityRepository.findByCityAndCountryEntity(
                city.getCity(),
                queryCountry(city.getCountry()))
                .stream()
                .findFirst();

        if(!cityEntity.isPresent()){
            throw new UnknownCityException(city, "City unknown");
        }

        cityEntity.get().setCity(updatedCity.getCity());
        cityEntity.get().setCountryEntity(queryCountry(updatedCity.getCountry()));
        cityEntity.get().setLastUpdate(new Timestamp((new Date()).getTime()));

        try{
            cityRepository.save(cityEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }
    }

    @Override
    public void deleteCity(City city) throws UnknownCityException, UnknownCountryException{
        Optional<CityEntity> cityEntity = cityRepository.findByCityAndCountryEntity(
                city.getCity(),
                queryCountry(city.getCountry()))
                .stream()
                .findFirst();

        if(!cityEntity.isPresent()){
            throw new UnknownCityException(city, "City unknown");
        }

        try{
            cityRepository.delete(cityEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR:" +e.getMessage());
        }
    }

    protected CountryEntity queryCountry(String country) throws UnknownCountryException {
        Optional<CountryEntity> countryEntity = countryRepository.findByCountry(country).stream()
                .filter(entity -> entity.getCountry().equals(country))
                .findFirst();
        if( !countryEntity.isPresent()){
            throw new UnknownCountryException("Country unknown"+ country);
        }
        return countryEntity.get();
    }
}
