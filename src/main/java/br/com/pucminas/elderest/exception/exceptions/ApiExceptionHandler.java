package br.com.pucminas.elderest.exception.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<GlobalError> internalServerError(final InternalErrorException e) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("erro", e.getMessage());
        final GlobalError globalError = new GlobalError(HttpStatus.INTERNAL_SERVER_ERROR.value(), errors);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(globalError);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GlobalError> badRequest(final BadRequestException e) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("erro", e.getMessage());
        final GlobalError globalError = new GlobalError(HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalError);
    }

    @ExceptionHandler(DuplicateEntity.class)
    public ResponseEntity<GlobalError> duplicateEntity(final DuplicateEntity e) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("erro", e.getMessage());
        final GlobalError globalError = new GlobalError(HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalError);
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    public ResponseEntity<GlobalError> unauthorizedError(final UserNotAuthorizedException e) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("erro", e.getMessage());
        final GlobalError globalError = new GlobalError(HttpStatus.UNAUTHORIZED.value(), errors);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(globalError);
    }

    @ExceptionHandler(ForbidenException.class)
    public ResponseEntity<GlobalError> forbiddenError(final ForbidenException e) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("erro", e.getMessage());
        final GlobalError globalError = new GlobalError(HttpStatus.FORBIDDEN.value(), errors);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(globalError);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalError> notFoundError(final NotFoundException e) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("erro", e.getMessage());
        final GlobalError globalError = new GlobalError(HttpStatus.NOT_FOUND.value(), errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(globalError);
    }

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<GlobalError> notAcceptable(final NotAcceptableException e) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("erro", e.getMessage());
        final GlobalError globalError = new GlobalError(HttpStatus.NOT_ACCEPTABLE.value(), errors);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(globalError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GlobalError> handleConstraintViolationException(final ConstraintViolationException e) {
        final Map<String, String> errors = new HashMap<>();

        final Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        for(final ConstraintViolation<?> constraintViolation : constraintViolations) {
            errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }

        final GlobalError globalError = new GlobalError(HttpStatus.BAD_REQUEST.value(), errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalError> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {

        final Map<String, String> errors = new HashMap<>();

        final BindingResult bindingResult = e.getBindingResult();
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for(final FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        final GlobalError globalError = new GlobalError(HttpStatus.BAD_REQUEST.value(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalError> unknowException(final Exception e) {
        final Map<String, String> errors = new HashMap<>();
        errors.put("erro", e.getMessage());
        final GlobalError globalError = new GlobalError(HttpStatus.INTERNAL_SERVER_ERROR.value(), errors);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(globalError);
    }
}
