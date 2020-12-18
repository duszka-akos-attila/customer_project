package Application.Exception;

import Application.Model.Address;
import lombok.Data;

@Data
public class UnknownAddressException extends Exception{
    private Address address;

    public UnknownAddressException(Address address){
        this.address = address;
    }

    public UnknownAddressException(String message){
        super(message);
    }

    public UnknownAddressException(Address address, String message){
        super(message);
        this.address = address;
    }
}
