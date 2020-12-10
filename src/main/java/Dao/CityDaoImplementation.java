package Dao;

import Dao.Entity.CityEntity;
import Dao.Entity.CountryEntity;
import Dao.Repository.CityRepository;
import Dao.Repository.CountryRepository;
import Model.City;
import Exception.UnknownCityException;
import Exception.UnknownCountryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
                        entity.getCountryEntity().getCountryId(),
                        entity.getLastUpdate()
                )).
                collect(Collectors.toList());
    }

    @Override
    public void createCity(City city) throws UnknownCountryException {

        CityEntity cityEntity;

        cityEntity = CityEntity.builder()
                .city(city.getCity())
                .countryEntity(queryCountry(city.getCountryId()))
                .lastUpdate(city.getLastUpdate())
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
        Optional<CityEntity> cityEntity = cityRepository.findByCityAndCountry(
                city.getCity(),
                queryCountry(city.getCountryId()))
                .stream()
                .findFirst();

        if(!cityEntity.isPresent()){
            throw new UnknownCityException(city, "City unknown");
        }

        cityEntity.get().setCity(updatedCity.getCity());
        cityEntity.get().setCountryEntity(queryCountry(updatedCity.getCountryId()));
        cityEntity.get().setLastUpdate(updatedCity.getLastUpdate());

        try{
            cityRepository.save(cityEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }
    }

    @Override
    public void deleteCity(City city) throws UnknownCityException, UnknownCountryException{
        Optional<CityEntity> cityEntity = cityRepository.findByCityAndCountry(
                city.getCity(),
                queryCountry(city.getCountryId()))
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

    protected CountryEntity queryCountry(int countryId) throws UnknownCountryException {
        Optional<CountryEntity> countryEntity = countryRepository.findByCountryId(countryId).stream()
                .filter(entity -> entity.getCountryId() == (countryId))
                .findFirst();
        if( !countryEntity.isPresent()){
            throw new UnknownCountryException("Country unknown");
        }
        return countryEntity.get();
    }
}
