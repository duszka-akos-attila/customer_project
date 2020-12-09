package Service;

import Model.Customer;

import java.util.Collection;

public interface CustomerService {

    Collection<Customer> getAllCustomers();

    void recordCustomer(Customer customer);

    void updateFirstMatch(Customer originalCustomer, Customer updatedCustomer);

    void deleteCustomer(Customer customer);
}
