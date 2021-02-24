package ru.gb.springbase.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(String message) {
        super("No Data Found " + message);
    }
}
