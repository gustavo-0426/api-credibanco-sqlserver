package com.co.softworld.credibanco.exception;

import com.co.softworld.credibanco.model.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

import static com.co.softworld.credibanco.util.IUtility.EMPTY;
import static com.co.softworld.credibanco.util.IUtility.FORMAT_DATETIME;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionController implements IExceptionController {

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> violationException(MethodArgumentNotValidException notValidException, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(now().format(FORMAT_DATETIME));
        error.setStatus(BAD_REQUEST.value());
        error.setPatch(request.getServletPath());
        error.setMessage(notValidException.getFieldError() != null ? notValidException.getFieldError().getDefaultMessage() : EMPTY);
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> typeMismatchException(MethodArgumentTypeMismatchException matchException, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(now().format(FORMAT_DATETIME));
        error.setStatus(BAD_REQUEST.value());
        error.setPatch(request.getServletPath());
        error.setMessage(matchException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }
}
