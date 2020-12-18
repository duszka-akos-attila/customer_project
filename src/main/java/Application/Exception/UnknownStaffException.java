package Application.Exception;

import Application.Model.Staff;
import lombok.Data;

@Data
public class UnknownStaffException extends Exception{
    private Staff staff;

    public UnknownStaffException(Staff staff){
        this.staff = staff;
    }

    public UnknownStaffException(String message){
        super(message);
    }

    public UnknownStaffException(Staff staff, String message){
        super(message);
        this.staff = staff;
    }
}
