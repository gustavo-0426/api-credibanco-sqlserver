package com.co.softworld.credibanco.exception;

import com.co.softworld.credibanco.model.Error;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

public interface IExceptionController {

        ResponseEntity<Error> violationException(MethodArgumentNotValidException notValidException, HttpServletRequest request);
        ResponseEntity<Error> typeMismatchException(MethodArgumentTypeMismatchException matchException, HttpServletRequest request);
        ResponseEntity<Error> transactionException(InvalidTransactionException transactionException, HttpServletRequest request);
        ResponseEntity<Error> productException(InvalidProductException productException, HttpServletRequest request);
        ResponseEntity<Error> cardException(InvalidCardException cardException, HttpServletRequest request);
        ResponseEntity<Error> sqlException(SQLServerException sqlException, HttpServletRequest request);
}
