package Application.Dao;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.CityEntity;
import Application.Dao.Entity.CountryEntity;
import Application.Dao.Repository.AddressRepository;
import Application.Dao.Repository.CityRepository;
import Application.Dao.Repository.CountryRepository;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownCityException;
import Application.Exception.UnknownCountryException;
import Application.Model.Address;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AddressDaoImplementation implements AddressDao {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Override
    public Collection<Address> readAll() {
        return StreamSupport.stream(addressRepository.findAll().spliterator(), false)
                .map(entity -> new Address(
                        entity.getAddress(),
                        entity.getAddress2(),
                        entity.getDistrict(),
                        entity.getCityEntity().getCity(),
                        entity.getCityEntity().getCountryEntity().getCountry(),
                        entity.getPostalCode(),
                        entity.getPhone()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createAddress(Address address) throws UnknownCityException {
        AddressEntity addressEntity;
        GeometryFactory geometryFactory = new GeometryFactory();

        addressEntity = AddressEntity.builder()
                .address(address.getAddress())
                .address2(address.getAddress2())
                .district(address.getDistrict())
                .cityEntity(queryCity(address.getCity(), address.getCountry()))
                .postalCode(address.getPostalCode())
                .phone(address.getPhone())
                .location(geometryFactory.createPoint(new Coordinate()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();

        try {
            addressRepository.save(addressEntity);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Address address, Address updatedAddress) throws UnknownAddressException, UnknownCountryException, UnknownCityException {
        Optional<AddressEntity> addressEntity = addressRepository.findByAddressAndAddress2AndDistrictAndCityEntityAndPostalCodeAndPhone(
                address.getAddress(),
                address.getAddress2(),
                address.getDistrict(),
                queryCity(address.getCity(), address.getCountry()),
                address.getPostalCode(),
                address.getPhone())
                .stream()
                .findFirst();

        if (!addressEntity.isPresent()) {
            throw new UnknownAddressException(address, "Address unknown: " + address.toString());
        }

        addressEntity.get().setAddress(updatedAddress.getAddress());
        addressEntity.get().setAddress2(updatedAddress.getAddress2());
        addressEntity.get().setDistrict(updatedAddress.getDistrict());
        addressEntity.get().setCityEntity(queryCity(updatedAddress.getCity(), updatedAddress.getCountry()));
        addressEntity.get().setPostalCode(updatedAddress.getPostalCode());
        addressEntity.get().setPhone(updatedAddress.getPhone());
        addressEntity.get().setLastUpdate(new Timestamp((new Date()).getTime()));

        try {
            addressRepository.save(addressEntity.get());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void deleteAddress(Address address) throws UnknownAddressException, UnknownCityException {
        Optional<AddressEntity> addressEntity = addressRepository.findByAddressAndAddress2AndDistrictAndCityEntityAndPostalCodeAndPhone(
                address.getAddress(),
                address.getAddress2(),
                address.getDistrict(),
                queryCity(address.getCity(), address.getCountry()),
                address.getPostalCode(),
                address.getPhone())
                .stream()
                .findFirst();

        if (!addressEntity.isPresent()) {
            throw new UnknownAddressException(address, "Address unknown");
        }

        try {
            addressRepository.delete(addressEntity.get());
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
        }
    }

    protected CityEntity queryCity(String city, String country) throws UnknownCityException {
        Optional<CityEntity> cityEntity = cityRepository.findByCity(city).stream()
                .filter(entity ->
                        entity.getCountryEntity().getCountry().equals(country))
                .findFirst();
        if (!cityEntity.isPresent()) {
            Optional<CountryEntity> countryEntity = countryRepository.findByCountry(country);
            if (!countryEntity.isPresent()) {
                throw new UnknownCityException("There is no city, named: " + city);
            }
        }
        return cityEntity.get();
    }
}
