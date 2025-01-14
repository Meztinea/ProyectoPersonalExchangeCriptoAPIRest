package mx.exchange.Api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GestionadorDeErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(ErrorDTO::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarError404(EntityNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
                //ResponseEntity.notFound(ex.getMessage()).build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity gestionaError403(AccessDeniedException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso Denegado: " + ex.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity gestionaError405(HttpRequestMethodNotSupportedException ex){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Método HTTP no permitido: " + ex.getMethod());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity gestionaError409(DataIntegrityViolationException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Restricción de integridad violada: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity gestionaError500(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Un error inesperado ha ocurrido: " + ex.getMessage());
    }

    private record ErrorDTO(String error, String mensaje){
        public ErrorDTO(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
