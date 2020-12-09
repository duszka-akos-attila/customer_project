package Dao;

import Model.Customer;

import java.util.Collection;

public interface CustomerDao {

    Collection<Customer> readAll();

    void createCustomer(Customer customer);

    void updateFirstMatch(Customer customer, Customer updatedCustomer);

    void deleteCustomer(Customer customer);
}
