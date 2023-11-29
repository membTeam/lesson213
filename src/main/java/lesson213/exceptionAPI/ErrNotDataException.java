package lesson213.exceptionAPI;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrNotDataException extends RuntimeException {
    public ErrNotDataException(String err) {
        super(err);
    }
}
