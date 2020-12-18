package Application.Controller;

import Application.Controller.Dto.StaffDto;
import Application.Controller.Dto.StaffUpdateDto;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownStaffException;
import Application.Exception.UnknownStoreException;
import Application.Model.Staff;
import Application.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class StaffController {
    private final StaffService service;

    @GetMapping("/Staff")
    public Collection<StaffDto> listStaffs() {
        return service.getAllStaffs()
                .stream()
                .map(model -> StaffDto.builder()
                        .firstName(model.getFirstName())
                        .lastName(model.getLastName())
                        .address(model.getAddress())
                        //.picture(model.getPicture())
                        .email(model.getEmail())
                        .storeAddress(model.getStoreAddress())
                        .active(model.getActive())
                        .username(model.getUsername())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Staff")
    public void recordStaff(@RequestBody StaffDto staffDto) {
        try {
            service.recordStaff(new Staff(
                    staffDto.getFirstName(),
                    staffDto.getLastName(),
                    staffDto.getAddress(),
                    //staffDto.getPicture(),
                    staffDto.getEmail(),
                    staffDto.getStoreAddress(),
                    staffDto.getActive(),
                    staffDto.getUsername(),
                    staffDto.getPassword()
            ));
        } catch (UnknownStoreException | UnknownAddressException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Staff")
    public void deleteFirstMatchingStaff(@RequestBody StaffDto staffDto) {
        try {
            service.deleteStaff(new Staff(
                    staffDto.getFirstName(),
                    staffDto.getLastName(),
                    staffDto.getAddress(),
                    //staffDto.getPicture(),
                    staffDto.getEmail(),
                    staffDto.getStoreAddress(),
                    staffDto.getActive(),
                    staffDto.getUsername(),
                    staffDto.getPassword()
            ));
        } catch (UnknownStoreException | UnknownAddressException | UnknownStaffException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/Staff")
    public void updateFirstMatchingStaff(@RequestBody StaffUpdateDto staffUpdateDto) {
        try {
            service.updateFirstMatch(
                    new Staff(
                            staffUpdateDto.getFirstName(),
                            staffUpdateDto.getLastName(),
                            staffUpdateDto.getAddress(),
                            //staffUpdateDto.getPicture(),
                            staffUpdateDto.getEmail(),
                            staffUpdateDto.getStoreAddress(),
                            staffUpdateDto.getActive(),
                            staffUpdateDto.getUsername(),
                            staffUpdateDto.getPassword()
                    ),
                    new Staff(
                            staffUpdateDto.getUpdatedFirstName(),
                            staffUpdateDto.getUpdatedLastName(),
                            staffUpdateDto.getUpdatedAddress(),
                            //staffUpdateDto.getUpdatedPicture(),
                            staffUpdateDto.getUpdatedEmail(),
                            staffUpdateDto.getUpdatedStoreAddress(),
                            staffUpdateDto.getUpdatedActive(),
                            staffUpdateDto.getUpdatedUsername(),
                            staffUpdateDto.getUpdatedPassword()
                    )
            );
        } catch (UnknownStoreException | UnknownAddressException | UnknownStaffException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
