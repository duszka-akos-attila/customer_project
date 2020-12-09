package Service;

import Dao.CustomerDao;
import Model.Customer;
import Exception.UnknownAddressException;
import Exception.UnknownStoreException;
import Exception.UnknownCustomerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplementation implements CustomerService{

    private final CustomerDao customerDao;

    @Override
    public Collection<Customer> getAllCustomers() {
        return customerDao.readAll();
    }

    @Override
    public void recordCustomer(Customer customer) throws UnknownAddressException, UnknownStoreException {
        customerDao.createCustomer(customer);
    }

    @Override
    public void updateFirstMatch(Customer originalCustomer, Customer updatedCustomer) throws UnknownCustomerException, UnknownAddressException, UnknownStoreException {
        customerDao.updateFirstMatch(originalCustomer, updatedCustomer);
    }

    @Override
    public void deleteCustomer(Customer customer) throws UnknownCustomerException, UnknownAddressException, UnknownStoreException {
        customerDao.deleteCustomer(customer);
    }
}
