package com.example.aggregator.api.configuration.exception.handler;

import com.example.aggregator.api.configuration.exception.NotFoundException;
import com.example.aggregator.api.configuration.exception.RestException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RestException.class})
    public ResponseEntity<ErrorMessageResponse> handleRestException(Exception ex,
                                                                    WebRequest request) {
        RestException restException = (RestException) ex;

        int status = restException.getStatus().value();

        ErrorMessageResponse error = new ErrorMessageResponse(status,
                restException.getMessage());

        return new ResponseEntity<>(error, restException.getStatus());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleBadCredentialsException(NotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorMessageResponse(
                        ex.getStatus().value(),
                        ex.getMessage()
                ),ex.getStatus()
        );
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ErrorMessageResponse {
        private int status;
        private String message;

        private ErrorMessageResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }

        private ErrorMessageResponse(RestException ex) {
            status = ex.getStatus().value();
            message = ex.getMessage();
        }
    }
}
