package Application.Exception;

import Application.Model.Store;
import lombok.Data;

@Data
public class UnknownStoreException extends Exception{
    private Store store;

    public UnknownStoreException(Store store){
        this.store = store;
    }

    public UnknownStoreException(String message){
        super(message);
    }

    public UnknownStoreException(Store store, String message){
        super(message);
        this.store = store;
    }
}
