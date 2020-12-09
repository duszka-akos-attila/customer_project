package Dao;

import Model.Customer;
import Exception.UnknownAddressException;
import Exception.UnknownCustomerException;
import Exception.UnknownStoreException;

import java.util.Collection;

public interface CustomerDao {

    Collection<Customer> readAll();

    void createCustomer(Customer customer) throws UnknownStoreException, UnknownAddressException;

    void updateFirstMatch(Customer customer, Customer updatedCustomer) throws UnknownCustomerException, UnknownStoreException, UnknownAddressException;

    void deleteCustomer(Customer customer) throws UnknownCustomerException, UnknownStoreException, UnknownAddressException;
}
