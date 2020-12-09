package Exception;

import Model.Customer;
import lombok.Data;

@Data
public class UnknownCustomerException extends Exception{
    private Customer customer;

    public UnknownCustomerException(Customer customer){
        this.customer = customer;
    }

    public UnknownCustomerException(String message){
        super(message);
    }

    public UnknownCustomerException(Customer customer, String message){
        super(message);
        this.customer = customer;
    }
}
