package com.co.softworld.credibanco.exception;

import com.co.softworld.credibanco.model.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

public interface IExceptionController {

        ResponseEntity<Error> violationException(MethodArgumentNotValidException notValidException, HttpServletRequest request);
        ResponseEntity<Error> typeMismatchException(MethodArgumentTypeMismatchException matchException, HttpServletRequest request);
}
