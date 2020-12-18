package Application.Controller;

import Application.Controller.Dto.CityDto;
import Application.Controller.Dto.CityUpdateDto;
import Application.Controller.Dto.StoreDto;
import Application.Controller.Dto.StoreUpdateDto;
import Application.Exception.*;
import Application.Model.City;
import Application.Model.Store;
import Application.Service.CityService;
import Application.Service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService service;

    @GetMapping("/Store")
    public Collection<StoreDto> listStores(){
        return service.getAllStores()
                .stream()
                .map( model -> StoreDto.builder()
                        .managerFirstName(model.getManagerFirstName())
                        .managerLastName(model.getManagerLastName())
                        .address(model.getAddress())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Store")
    public void recordStore(@RequestBody StoreDto storeDto){
        try{
            service.recordStore(new Store(
                    storeDto.getManagerFirstName(),
                    storeDto.getManagerLastName(),
                    storeDto.getAddress()
            ));
        } catch(UnknownStaffException | UnknownAddressException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Store")
    public void deleteFirstMatchingStore(@RequestBody StoreDto storeDto){
        try{
            service.deleteStore(new Store(
                    storeDto.getManagerFirstName(),
                    storeDto.getManagerLastName(),
                    storeDto.getAddress()
            ));
        } catch(UnknownStaffException | UnknownAddressException | UnknownStoreException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/Store")
    public void updateFirstMatchingStore(@RequestBody StoreUpdateDto storeUpdateDto){
        try{
            service.updateFirstMatch(
                    new Store(
                            storeUpdateDto.getManagerFirstName(),
                            storeUpdateDto.getManagerLastName(),
                            storeUpdateDto.getAddress()
                    ),
                    new Store(
                            storeUpdateDto.getUpdatedManagerFirstName(),
                            storeUpdateDto.getUpdatedManagerLastName(),
                            storeUpdateDto.getUpdatedAddress()
                    )
            );
        } catch(UnknownStaffException | UnknownAddressException | UnknownStoreException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
