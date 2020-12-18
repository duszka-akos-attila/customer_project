package Application.Dao;

import Application.Exception.UnknownAddressException;
import Application.Exception.UnknownCustomerException;
import Application.Exception.UnknownStoreException;
import Application.Model.Customer;

import java.util.Collection;

public interface CustomerDao {

    Collection<Customer> readAll();

    void createCustomer(Customer customer) throws UnknownStoreException, UnknownAddressException;

    void updateFirstMatch(Customer customer, Customer updatedCustomer) throws UnknownCustomerException, UnknownStoreException, UnknownAddressException;

    void deleteCustomer(Customer customer) throws UnknownCustomerException, UnknownStoreException, UnknownAddressException;
}
