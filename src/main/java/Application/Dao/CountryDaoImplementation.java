package Application.Dao;

import Application.Dao.Entity.CountryEntity;
import Application.Dao.Repository.CountryRepository;
import Application.Model.Country;
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
public class CountryDaoImplementation implements CountryDao{

    private final CountryRepository countryRepository;

    @Override
    public Collection<Country> readAll() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(), false)
                .map(entity -> new Country(
                        entity.getCountry()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createCountry(Country country) {
        CountryEntity countryEntity;

        countryEntity = CountryEntity.builder()
                .country(country.getCountry())
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        try{
            countryRepository.save(countryEntity);
        }
        catch(Exception e){
            System.out.println("ERROR:" +e.getMessage());
        }


    }

    @Override
    public void updateFirstMatch(Country country, Country updatedCountry) throws UnknownCountryException {
        Optional<CountryEntity> countryEntity = countryRepository.findByCountry(
                country.getCountry())
                .stream()
                .findFirst();

        if(!countryEntity.isPresent()){
            throw new UnknownCountryException(country, "Country unknown");
        }

        countryEntity.get().setCountry(updatedCountry.getCountry());
        countryEntity.get().setLastUpdate(new Timestamp((new Date()).getTime()));

        try{
            countryRepository.save(countryEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }

    }

    @Override
    public void deleteCountry(Country country) throws UnknownCountryException {
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
