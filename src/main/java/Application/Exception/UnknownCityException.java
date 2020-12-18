package Application.Exception;

import Application.Model.City;
import lombok.Data;

@Data
public class UnknownCityException extends Exception {
    private City city;

    public UnknownCityException(City city) {
        this.city = city;
    }

    public UnknownCityException(String message) {
        super(message);
    }

    public UnknownCityException(City city, String message) {
        super(message);
        this.city = city;
    }
}
