package Dao;

import Dao.Entity.AddressEntity;
import Dao.Entity.CustomerEntity;
import Dao.Entity.StoreEntity;
import Dao.Repository.AddressRepository;
import Dao.Repository.CustomerRepository;
import Dao.Repository.StoreRepository;
import Model.Customer;
import Exception.UnknownAddressException;
import Exception.UnknownCustomerException;
import Exception.UnknownStoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerDaoImplementation implements CustomerDao{

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;

    @Override
    public Collection<Customer> readAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(),false)
                .map(entity -> new Customer(
                        entity.getStoreEntity().getStoreId(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getEmail(),
                        entity.getAddressEntity().getAddressId(),
                        entity.getActive(),
                        entity.getCreateDate(),
                        entity.getLastUpdate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createCustomer(Customer customer) throws UnknownStoreException, UnknownAddressException {
        CustomerEntity customerEntity;

        customerEntity = CustomerEntity.builder()
                .storeEntity(queryStore(customer.getStoreId()))
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .addressEntity(queryAddress(customer.getAddressId()))
                .active(customer.getActive())
                .createDate(customer.getCreateDate())
                .lastUpdate(customer.getLastUpdate())
                .build();

        try{
            customerRepository.save(customerEntity);
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }
    }

    @Override
    public void updateFirstMatch(Customer customer, Customer updatedCustomer) throws UnknownCustomerException, UnknownStoreException, UnknownAddressException {
        Optional<CustomerEntity> customerEntity = customerRepository.findByStoreAndFirstNameAndLastNameAndEmailAndAddressAndActiveAndCreateDate(
                queryStore(customer.getStoreId()),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                queryAddress(customer.getAddressId()),
                customer.getActive(),
                customer.getCreateDate())
                .stream()
                .findFirst();

        if(!customerEntity.isPresent()){
            throw new UnknownCustomerException(customer, "Customer unknown");
        }

        customerEntity.get().setStoreEntity(queryStore(updatedCustomer.getStoreId()));
        customerEntity.get().setFirstName(updatedCustomer.getFirstName());
        customerEntity.get().setLastName(updatedCustomer.getLastName());
        customerEntity.get().setEmail(updatedCustomer.getEmail());
        customerEntity.get().setAddressEntity(queryAddress(updatedCustomer.getAddressId()));
        customerEntity.get().setActive(updatedCustomer.getActive());
        customerEntity.get().setCreateDate(updatedCustomer.getCreateDate());
        customerEntity.get().setLastUpdate(updatedCustomer.getLastUpdate());

        try{
            customerRepository.save(customerEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR: " +e.getMessage());
        }
    }

    @Override
    public void deleteCustomer(Customer customer) throws UnknownCustomerException, UnknownStoreException, UnknownAddressException {
        Optional<CustomerEntity> customerEntity = customerRepository.findByStoreAndFirstNameAndLastNameAndEmailAndAddressAndActiveAndCreateDate(
                queryStore(customer.getStoreId()),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                queryAddress(customer.getAddressId()),
                customer.getActive(),
                customer.getCreateDate())
                .stream()
                .findFirst();

        if(!customerEntity.isPresent()){
            throw new UnknownCustomerException(customer, "Customer unknown");
        }

        try{
            customerRepository.delete(customerEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR:" +e.getMessage());
        }
    }

    protected AddressEntity queryAddress(int addressId) throws UnknownAddressException {
        Optional<AddressEntity> addressEntity = addressRepository.findByAddressId(addressId).stream()
                .filter(entity -> entity.getAddressId() == (addressId))
                .findFirst();
        if( !addressEntity.isPresent()){
            throw new UnknownAddressException("Address unknown");
        }
        return addressEntity.get();
    }

    protected StoreEntity queryStore(int storeId) throws UnknownStoreException {
        Optional<StoreEntity> storeEntity = storeRepository.findByStoreId(storeId).stream()
                .filter(entity -> entity.getStoreId() == (storeId))
                .findFirst();
        if( !storeEntity.isPresent()){
            throw new UnknownStoreException("Store unknown");
        }
        return storeEntity.get();
    }
}
