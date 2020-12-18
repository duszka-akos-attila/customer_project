package Application.Exception;

import Application.Model.Country;
import lombok.Data;

@Data
public class UnknownCountryException extends Exception{
    private Country country;

    public UnknownCountryException(Country country){
        this.country = country;
    }

    public UnknownCountryException(String message){
        super(message);
    }

    public UnknownCountryException(Country country, String message){
        super(message);
        this.country = country;
    }
}
