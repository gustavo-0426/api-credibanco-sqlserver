package com.co.softworld.credibanco.util;

import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public interface IUtility {
    DateTimeFormatter FORMAT_DATE = ofPattern("MM/yyyy");
    DateTimeFormatter FORMAT_DATETIME = ofPattern("dd-MM-yyyy HH:mm:ss");
    String VALID_NAME = "^[\\w]+\\s*[\\w]*";
    String EMPTY = "";
}
