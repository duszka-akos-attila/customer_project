package Service;

import Model.Customer;
import Exception.UnknownAddressException;
import Exception.UnknownStoreException;
import Exception.UnknownCustomerException;

import java.util.Collection;

public interface CustomerService {

    Collection<Customer> getAllCustomers();

    void recordCustomer(Customer customer) throws UnknownAddressException, UnknownStoreException;

    void updateFirstMatch(Customer originalCustomer, Customer updatedCustomer) throws UnknownCustomerException, UnknownAddressException, UnknownStoreException;

    void deleteCustomer(Customer customer) throws UnknownCustomerException, UnknownAddressException, UnknownStoreException;
}
