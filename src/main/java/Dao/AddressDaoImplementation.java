package Dao;

import Dao.Entity.AddressEntity;
import Dao.Entity.CityEntity;
import Dao.Repository.AddressRepository;
import Dao.Repository.CityRepository;
import Model.Address;
import Exception.UnknownAddressException;
import Exception.UnknownCityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AddressDaoImplementation implements AddressDao{

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;

    @Override
    public Collection<Address> readAll() {
        return StreamSupport.stream(addressRepository.findAll().spliterator(),false)
                .map(entity -> new Address(
                        entity.getAddress(),
                        entity.getAddress2(),
                        entity.getDistrict(),
                        entity.getCityEntity().getCityId(),
                        entity.getPostalCode(),
                        entity.getPhone(),
                        entity.getLastUpdate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createAddress(Address address) throws UnknownCityException {
        AddressEntity addressEntity;

        addressEntity = AddressEntity.builder()
                .address(address.getAddress())
                .address2(address.getAddress2())
                .district(address.getDistrict())
                .cityEntity(queryCity(address.getCityId()))
                .postalCode(address.getPostalCode())
                .phone(address.getPhone())
                .lastUpdate(address.getLastUpdate())
                .build();

        try{
            addressRepository.save(addressEntity);
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Address address, Address updatedAddress) throws UnknownAddressException, UnknownCityException {
        Optional<AddressEntity> addressEntity = addressRepository.findByAddressAndAddress2AndDistrictAndCityAndPostalCodeAndPhone(
                address.getAddress(),
                address.getAddress2(),
                address.getDistrict(),
                queryCity(address.getCityId()),
                address.getPostalCode(),
                address.getPhone())
                .stream()
                .findFirst();

        if(!addressEntity.isPresent()){
            throw new UnknownAddressException(address, "Address unknown");
        }

        addressEntity.get().setAddress(updatedAddress.getAddress());
        addressEntity.get().setAddress2(updatedAddress.getAddress2());
        addressEntity.get().setDistrict(updatedAddress.getDistrict());
        addressEntity.get().setCityEntity(queryCity(updatedAddress.getCityId()));
        addressEntity.get().setPostalCode(updatedAddress.getPostalCode());
        addressEntity.get().setPhone(updatedAddress.getPhone());
        addressEntity.get().setLastUpdate(updatedAddress.getLastUpdate());

        try{
            addressRepository.save(addressEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }
    }

    @Override
    public void deleteAddress(Address address) throws UnknownAddressException, UnknownCityException {
        Optional<AddressEntity> addressEntity = addressRepository.findByAddressAndAddress2AndDistrictAndCityAndPostalCodeAndPhone(
                address.getAddress(),
                address.getAddress2(),
                address.getDistrict(),
                queryCity(address.getCityId()),
                address.getPostalCode(),
                address.getPhone())
                .stream()
                .findFirst();

        if(!addressEntity.isPresent()){
            throw new UnknownAddressException(address, "Address unknown");
        }

        try{
            addressRepository.delete(addressEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR:" +e.getMessage());
        }
    }

    protected CityEntity queryCity(int cityId) throws UnknownCityException {
        Optional<CityEntity> cityEntity = cityRepository.findByCityId(cityId).stream()
                .filter(entity -> entity.getCityId() == (cityId))
                .findFirst();
        if( !cityEntity.isPresent()){
            throw new UnknownCityException("There is no city, with ID: "+cityId);
        }
        return cityEntity.get();
    }
}
