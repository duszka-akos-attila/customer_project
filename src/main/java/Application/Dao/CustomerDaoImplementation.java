package Application.Dao;

import Application.Dao.Entity.AddressEntity;
import Application.Dao.Entity.CustomerEntity;
import Application.Dao.Entity.StoreEntity;
import Application.Dao.Repository.AddressRepository;
import Application.Dao.Repository.CustomerRepository;
import Application.Dao.Repository.StoreRepository;
import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownCustomerException;
import Application.Exception.UnknownStoreException;
import Application.Model.Customer;
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
public class CustomerDaoImplementation implements CustomerDao {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;

    @Override
    public Collection<Customer> readAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(entity -> new Customer(
                        entity.getStoreEntity().getAddressEntity().getAddress(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getEmail(),
                        entity.getAddressEntity().getAddress(),
                        entity.getActive()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createCustomer(Customer customer) throws UnknownStoreException, UnknownAddressException {
        CustomerEntity customerEntity;

        customerEntity = CustomerEntity.builder()
                .storeEntity(queryStore(customer.getStoreAddress()))
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .addressEntity(queryAddress(customer.getAddress()))
                .active(customer.getActive())
                .createDate(new Timestamp((new Date()).getTime()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();

        try {
            customerRepository.save(customerEntity);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Customer customer, Customer updatedCustomer) throws UnknownCustomerException, UnknownStoreException, UnknownAddressException {
        Optional<CustomerEntity> customerEntity = customerRepository.findByStoreEntityAndFirstNameAndLastNameAndEmailAndAddressEntityAndActive(
                queryStore(customer.getStoreAddress()),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                queryAddress(customer.getAddress()),
                customer.getActive())
                .stream()
                .findFirst();

        if (!customerEntity.isPresent()) {
            throw new UnknownCustomerException(customer, "Customer unknown");
        }

        customerEntity.get().setStoreEntity(queryStore(updatedCustomer.getStoreAddress()));
        customerEntity.get().setFirstName(updatedCustomer.getFirstName());
        customerEntity.get().setLastName(updatedCustomer.getLastName());
        customerEntity.get().setEmail(updatedCustomer.getEmail());
        customerEntity.get().setAddressEntity(queryAddress(updatedCustomer.getAddress()));
        customerEntity.get().setActive(updatedCustomer.getActive());
        customerEntity.get().setLastUpdate(new Timestamp((new Date()).getTime()));

        try {
            customerRepository.save(customerEntity.get());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    @Override
    public void deleteCustomer(Customer customer) throws UnknownCustomerException, UnknownStoreException, UnknownAddressException {
        Optional<CustomerEntity> customerEntity = customerRepository.findByStoreEntityAndFirstNameAndLastNameAndEmailAndAddressEntityAndActive(
                queryStore(customer.getStoreAddress()),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                queryAddress(customer.getAddress()),
                customer.getActive())
                .stream()
                .findFirst();

        if (!customerEntity.isPresent()) {
            throw new UnknownCustomerException(customer, "Customer unknown");
        }

        try {
            customerRepository.delete(customerEntity.get());
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
        }
    }

    protected AddressEntity queryAddress(String address) throws UnknownAddressException {
        Optional<AddressEntity> addressEntity = addressRepository.findByAddress(address).stream()
                .filter(entity -> entity.getAddress().equals(address))
                .findFirst();
        if (!addressEntity.isPresent()) {
            throw new UnknownAddressException("Address unknown");
        }
        return addressEntity.get();
    }

    protected StoreEntity queryStore(String address) throws UnknownStoreException {
        Optional<StoreEntity> storeEntity = storeRepository.findByAddressEntity_Address(address).stream()
                .filter(entity -> entity.getAddressEntity().getAddress().equals(address))
                .findFirst();
        if (!storeEntity.isPresent()) {
            throw new UnknownStoreException("Store unknown");
        }
        return storeEntity.get();
    }
}
