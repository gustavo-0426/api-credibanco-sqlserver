package com.co.softworld.credibanco.exception;

import com.co.softworld.credibanco.model.Error;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;

import static com.co.softworld.credibanco.util.IUtility.EMPTY;
import static com.co.softworld.credibanco.util.IUtility.FORMAT_DATETIME;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionControllerImpl implements IExceptionController {

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

    @Override
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Error> transactionException(InvalidTransactionException transactionException, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(now().format(FORMAT_DATETIME));
        error.setStatus(BAD_REQUEST.value());
        error.setPatch(request.getServletPath());
        error.setMessage(transactionException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<Error> productException(InvalidProductException productException, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(now().format(FORMAT_DATETIME));
        error.setStatus(BAD_REQUEST.value());
        error.setPatch(request.getServletPath());
        error.setMessage(productException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(InvalidCardException.class)
    public ResponseEntity<Error> cardException(InvalidCardException cardException, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(now().format(FORMAT_DATETIME));
        error.setStatus(BAD_REQUEST.value());
        error.setPatch(request.getServletPath());
        error.setMessage(cardException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(SQLServerException.class)
    public ResponseEntity<Error> sqlException(SQLServerException sqlException, HttpServletRequest request) {
        Error error = new Error();
        error.setDate(now().format(FORMAT_DATETIME));
        error.setStatus(BAD_REQUEST.value());
        error.setPatch(request.getServletPath());
        error.setMessage(sqlException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

}
