package Application.Controller;

import Application.Controller.Dto.CustomerDto;
import Application.Controller.Dto.CustomerUpdateDto;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownCustomerException;
import Application.Exception.UnknownStoreException;
import Application.Model.Customer;
import Application.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/Customer")
    public Collection<CustomerDto> listCustomers() {
        return service.getAllCustomers()
                .stream()
                .map(model -> CustomerDto.builder()
                        .storeAddress(model.getStoreAddress())
                        .firstName(model.getFirstName())
                        .lastName(model.getLastName())
                        .email(model.getEmail())
                        .address(model.getAddress())
                        .active(model.getActive())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/Customer")
    public void recordCustomer(@RequestBody CustomerDto customerDto) {
        try {
            service.recordCustomer(new Customer(
                    customerDto.getStoreAddress(),
                    customerDto.getFirstName(),
                    customerDto.getLastName(),
                    customerDto.getEmail(),
                    customerDto.getAddress(),
                    customerDto.getActive()
            ));
        } catch (UnknownAddressException | UnknownStoreException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/Customer")
    public void deleteFirstMatchingCustomer(@RequestBody CustomerDto customerDto) {
        try {
            service.deleteCustomer(new Customer(
                    customerDto.getStoreAddress(),
                    customerDto.getFirstName(),
                    customerDto.getLastName(),
                    customerDto.getEmail(),
                    customerDto.getAddress(),
                    customerDto.getActive()
            ));
        } catch (UnknownCustomerException | UnknownAddressException | UnknownStoreException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/Customer")
    public void updateFirstMatchingCustomer(@RequestBody CustomerUpdateDto customerUpdateDto) {
        try {
            service.updateFirstMatch(
                    new Customer(
                            customerUpdateDto.getStoreAddress(),
                            customerUpdateDto.getFirstName(),
                            customerUpdateDto.getLastName(),
                            customerUpdateDto.getEmail(),
                            customerUpdateDto.getAddress(),
                            customerUpdateDto.getActive()
                    ),
                    new Customer(
                            customerUpdateDto.getUpdatedStoreAddress(),
                            customerUpdateDto.getUpdatedFirstName(),
                            customerUpdateDto.getUpdatedLastName(),
                            customerUpdateDto.getUpdatedEmail(),
                            customerUpdateDto.getUpdatedAddress(),
                            customerUpdateDto.getUpdatedActive()
                    )
            );
        } catch (UnknownCustomerException | UnknownAddressException | UnknownStoreException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
