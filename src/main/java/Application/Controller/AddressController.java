package Application.Controller;

import Application.Controller.Dto.AddressDto;
import Application.Controller.Dto.AddressUpdateDto;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownCityException;
import Application.Exception.UnknownCountryException;
import Application.Model.Address;
import Application.Service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;

    @GetMapping("/Address")
    public Collection<AddressDto> listAddresses() {
        return service.getAllAddresses()
                .stream()
                .map(model -> AddressDto.builder()
                        .address(model.getAddress())
                        .address2(model.getAddress2())
                        .district(model.getDistrict())
                        .city(model.getCity())
                        .country(model.getCountry())
                        .postalCode(model.getPostalCode())
                        .phone(model.getPhone())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Address")
    public void recordAddress(@RequestBody AddressDto addressDto) {
        try {
            service.recordAddress(new Address(
                    addressDto.getAddress(),
                    addressDto.getAddress2(),
                    addressDto.getDistrict(),
                    addressDto.getCity(),
                    addressDto.getCountry(),
                    addressDto.getPostalCode(),
                    addressDto.getPhone()
            ));
        } catch (UnknownCityException | UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Address")
    public void deleteFirstMatchingAddress(@RequestBody AddressDto addressDto) {
        try {
            service.deleteAddress(new Address(
                    addressDto.getAddress(),
                    addressDto.getAddress2(),
                    addressDto.getDistrict(),
                    addressDto.getCity(),
                    addressDto.getCountry(),
                    addressDto.getPostalCode(),
                    addressDto.getPhone()
            ));
        } catch (UnknownAddressException | UnknownCityException | UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/Address")
    public void updateFirstMatchingAddress(@RequestBody AddressUpdateDto addressUpdateDto) {
        try {
            service.updateFirstMatch(
                    new Address(
                            addressUpdateDto.getAddress(),
                            addressUpdateDto.getAddress2(),
                            addressUpdateDto.getDistrict(),
                            addressUpdateDto.getCity(),
                            addressUpdateDto.getCountry(),
                            addressUpdateDto.getPostalCode(),
                            addressUpdateDto.getPhone()
                    ),
                    new Address(
                            addressUpdateDto.getUpdatedAddress(),
                            addressUpdateDto.getUpdatedAddress2(),
                            addressUpdateDto.getUpdatedDistrict(),
                            addressUpdateDto.getUpdatedCity(),
                            addressUpdateDto.getUpdatedCountry(),
                            addressUpdateDto.getUpdatedPostalCode(),
                            addressUpdateDto.getUpdatedPhone()
                    )
            );
        } catch (UnknownAddressException | UnknownCityException | UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
