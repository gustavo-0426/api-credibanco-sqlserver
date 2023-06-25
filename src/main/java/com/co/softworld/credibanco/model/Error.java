package com.co.softworld.credibanco.model;

import lombok.Data;

@Data
public class Error {
    private String date;
    private int status;
    private String patch;
    private String message;
}
