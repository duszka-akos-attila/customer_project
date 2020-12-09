package Dao;

import Model.Customer;

import java.util.Collection;

public interface CustomerDao {

    Collection<Customer> readAll();

    void createCustomer(Customer customer) throws Exception;

    void updateFirstMatch(Customer customer, Customer updatedCustomer) throws Exception;

    void deleteCustomer(Customer customer) throws Exception;
}
