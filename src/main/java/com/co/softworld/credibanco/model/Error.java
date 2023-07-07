package com.co.softworld.credibanco.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component()
public class Error {
    private String date;
    private int status;
    private String patch;
    private String message;
}
