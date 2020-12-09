package Dao;

import Dao.Entity.AddressEntity;
import Dao.Entity.CustomerEntity;
import Dao.Entity.StoreEntity;
import Dao.Repository.AddressRepository;
import Dao.Repository.CustomerRepository;
import Dao.Repository.StoreRepository;
import Model.Customer;
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
                        entity.getCustomerId(),
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
    public void createCustomer(Customer customer) throws Exception {
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
    public void updateFirstMatch(Customer customer, Customer updatedCustomer) throws Exception {
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
            throw new Exception("Unknown Customer : "+ customer.toString());
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
    public void deleteCustomer(Customer customer) throws Exception {
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
            throw new Exception("Unknown Customer: " +customer.toString());
        }

        try{
            customerRepository.delete(customerEntity.get());
        }
        catch(Exception e){
            System.out.println("ERROR:" +e.getMessage());
        }
    }

    protected AddressEntity queryAddress(int addressId) throws Exception {
        Optional<AddressEntity> addressEntity = addressRepository.findByAddressId(addressId).stream()
                .filter(entity -> entity.getAddressId() == (addressId))
                .findFirst();
        if( !addressEntity.isPresent()){
            throw new Exception("Unknown address");
        }
        return addressEntity.get();
    }

    protected StoreEntity queryStore(int storeId) throws Exception {
        Optional<StoreEntity> storeEntity = storeRepository.findByStoreId(storeId).stream()
                .filter(entity -> entity.getStoreId() == (storeId))
                .findFirst();
        if( !storeEntity.isPresent()){
            throw new Exception("Unknown store");
        }
        return storeEntity.get();
    }
}
