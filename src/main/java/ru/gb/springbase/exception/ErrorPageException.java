package ru.gb.springbase.exception;

public class ErrorPageException extends RuntimeException{
    public ErrorPageException(Integer page, Integer pageSize, Integer totalPage) {
        super(String.format("Error page number == %d or page size == %d value. Total page %d", page, pageSize, totalPage));
    }
}
