package Dao;

import Dao.Entity.CountryEntity;
import Dao.Repository.CountryRepository;
import Model.Country;
import Exception.UnknownCountryException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CountryDaoImplementation implements CountryDao{

    private final CountryRepository countryRepository;

    @Override
    public Collection<Country> readAll() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(), false)
                .map(entity -> new Country(
                        entity.getCountryId(),
                        entity.getCountry(),
                        entity.getLastUpdate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createCountry(Country country) {
        CountryEntity countryEntity;

        countryEntity = CountryEntity.builder()
                .countryId(country.getCountryId())
                .country(country.getCountry())
                .lastUpdate(country.getLastUpdate())
                .build();
        try{
            countryRepository.save(countryEntity);
        }
        catch(Exception e){
            System.out.println("ERROR:" +e.getMessage());
        }


    }

    @Override
    public void updateFirstMatch(Country country, Country updatedCountry) throws Exception {
        Optional<CountryEntity> countryEntity = countryRepository.findByCountry(
                country.getCountry())
                .stream()
                .findFirst();

        if(!countryEntity.isPresent()){
            throw new UnknownCountryException(country, "Country unknown");
        }

        countryEntity.get().setCountry(updatedCountry.getCountry());
        countryEntity.get().setLastUpdate(updatedCountry.getLastUpdate());

        try{
            countryRepository.save(countryEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }

    }

    @Override
    public void deleteCountry(Country country) throws Exception {
        Optional<CountryEntity> countryEntity = countryRepository.findByCountry(
                country.getCountry())
                .stream()
                .findFirst();

        if(!countryEntity.isPresent()){
            throw new UnknownCountryException(country, "Country unknown");
        }

        try{
            countryRepository.delete(countryEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR:" +e.getMessage());
        }

    }
}
