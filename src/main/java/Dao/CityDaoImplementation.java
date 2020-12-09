package Dao;

import Dao.Entity.CityEntity;
import Dao.Entity.CountryEntity;
import Dao.Repository.CityRepository;
import Dao.Repository.CountryRepository;
import Model.City;
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
                        entity.getCityId(),
                        entity.getCity(),
                        entity.getCountryEntity().getCountryId(),
                        entity.getLastUpdate()
                )).
                collect(Collectors.toList());
    }

    @Override
    public void createCity(City city) throws Exception {

        CityEntity cityEntity;

        cityEntity = CityEntity.builder()
                .cityId(city.getCityId())
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
    public void updateFirstMatch(City city, City updatedCity) throws Exception {
        Optional<CityEntity> cityEntity = cityRepository.findByCityAndCountry(
                city.getCity(),
                queryCountry(city.getCountryId()))
                .stream()
                .findFirst();

        if(!cityEntity.isPresent()){
            throw new Exception("Unknown City: "+ city.toString());
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
    public void deleteCity(City city) throws Exception {
        Optional<CityEntity> cityEntity = cityRepository.findByCityAndCountry(
                city.getCity(),
                queryCountry(city.getCountryId()))
                .stream()
                .findFirst();

        if(!cityEntity.isPresent()){
            throw new Exception("Unknown city: " +city.toString());
        }

        try{
            cityRepository.delete(cityEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR:" +e.getMessage());
        }
    }

    protected CountryEntity queryCountry(int countryId) throws Exception {
        Optional<CountryEntity> countryEntity = countryRepository.findByCountryId(countryId).stream()
                .filter(entity -> entity.getCountryId() == (countryId))
                .findFirst();
        if( !countryEntity.isPresent()){
            throw new Exception("Unknown country");
        }
        return countryEntity.get();
    }
}
