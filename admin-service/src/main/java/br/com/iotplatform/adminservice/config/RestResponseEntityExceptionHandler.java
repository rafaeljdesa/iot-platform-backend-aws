package br.com.iotplatform.adminservice.config;

import br.com.iotplatform.adminservice.dto.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
    public static final String LOG_DEFAULT_ERROR_MESSAGE = "The endpoint {} throw the following error: {}";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleException(RuntimeException ex, WebRequest request) {

        log.error(LOG_DEFAULT_ERROR_MESSAGE, getUri(request), ex.getMessage());

        ResponseError responseError = ResponseError.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .reason(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .message(ex.getMessage())
            .timestamp(LocalDateTime.now())
            .build();

        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.error(LOG_DEFAULT_ERROR_MESSAGE, getUri(request), ex.getMessage());

        ResponseError responseError = ResponseError.builder()
            .status(status.value())
            .reason(status.getReasonPhrase())
            .message(ex.getMessage())
            .timestamp(LocalDateTime.now())
            .build();

        if (ex instanceof MethodArgumentNotValidException) {
            responseError.setErrors(
                getErrors((MethodArgumentNotValidException) ex)
            );
            responseError.setMessage("Some arguments are not valid");
        }

        return new ResponseEntity<>(responseError, status);
    }

    private String getUri(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private Map<String, String> getErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
