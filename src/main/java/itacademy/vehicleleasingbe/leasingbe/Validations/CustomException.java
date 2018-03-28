package itacademy.vehicleleasingbe.leasingbe.Validations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)  // 400
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }

}
