package Application.Controller;

import Application.Controller.Dto.CountryDto;
import Application.Controller.Dto.CountryUpdateDto;
import Application.Exception.UnknownCountryException;
import Application.Model.Country;
import Application.Service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CountryService service;

    @GetMapping("/Country")
    public Collection<CountryDto> listCountries() {
        return service.getAllCountries()
                .stream()
                .map(model -> CountryDto.builder()
                        .country(model.getCountry())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Country")
    public void recordCountry(@RequestBody CountryDto countryDto) {
        service.recordCountry(new Country(
                countryDto.getCountry()
        ));
    }

    @DeleteMapping("/Country")
    public void deleteFirstMatchingCountry(@RequestBody CountryDto countryDto) {
        try {
            service.deleteCountry(new Country(
                    countryDto.getCountry()
            ));
        } catch (UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/Country")
    public void updateFirstMatchingCountry(@RequestBody CountryUpdateDto countryUpdateDto) {
        try {
            service.updateFirstMatch(
                    new Country(
                            countryUpdateDto.getCountry()
                    ),
                    new Country(
                            countryUpdateDto.getUpdatedCountry()
                    )
            );
        } catch (UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
