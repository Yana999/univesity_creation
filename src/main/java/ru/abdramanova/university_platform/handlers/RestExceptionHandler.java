package ru.abdramanova.university_platform.handlers;


import lombok.Getter;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.persistence.EntityNotFoundException;
import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<Object> handleNotFoundException(RuntimeException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }

    @ExceptionHandler({AuthenticationException.class, SessionAuthenticationException.class})
    public  ResponseEntity<Object> handleAuthenticationException(RuntimeException exception, HttpServletRequest request, HttpServletResponse response){
        return new ResponseEntity<>("User does not exist", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Unable to upload. File is too large!");
    }

    @Getter
    public class ErrorInfo {
        private final String url;
        private final String info;

        ErrorInfo(String url, String info) {
            this.url = url;
            this.info = info;
        }
    }
}
