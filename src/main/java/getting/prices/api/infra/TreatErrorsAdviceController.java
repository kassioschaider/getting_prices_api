package getting.prices.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TreatErrorsAdviceController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treatError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity treatError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataValidateError::new).toList());
    }

    private record DataValidateError(String field, String message) {
        public DataValidateError(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
