package Application.Controller;

import Application.Controller.Dto.CityDto;
import Application.Controller.Dto.CityUpdateDto;
import Application.Exception.UnknownCityException;
import Application.Exception.UnknownCountryException;
import Application.Model.City;
import Application.Service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CityController {
    private final CityService service;

    @GetMapping("/City")
    public Collection<CityDto> listCities() {
        return service.getAllCities()
                .stream()
                .map(model -> CityDto.builder()
                        .city(model.getCity())
                        .country(model.getCountry())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/City")
    public void recordCity(@RequestBody CityDto cityDto) {
        try {
            service.recordCity(new City(
                    cityDto.getCity(),
                    cityDto.getCountry()
            ));
        } catch (UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/City")
    public void deleteFirstMatchingCity(@RequestBody CityDto cityDto) {
        try {
            service.deleteCity(new City(
                    cityDto.getCity(),
                    cityDto.getCountry()
            ));
        } catch (UnknownCityException | UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/City")
    public void updateFirstMatchingCity(@RequestBody CityUpdateDto cityUpdateDto) {
        try {
            service.updateFirstMatch(
                    new City(
                            cityUpdateDto.getCity(),
                            cityUpdateDto.getCountry()
                    ),
                    new City(
                            cityUpdateDto.getUpdatedCity(),
                            cityUpdateDto.getUpdatedCountry()
                    )
            );
        } catch (UnknownCityException | UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
