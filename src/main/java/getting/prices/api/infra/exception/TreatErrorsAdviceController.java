package getting.prices.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity treatErrorBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity treatErrorAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failure!");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity treatErrorAccessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity treatError500(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getLocalizedMessage());
    }

    private record DataValidateError(String field, String message) {
        public DataValidateError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
