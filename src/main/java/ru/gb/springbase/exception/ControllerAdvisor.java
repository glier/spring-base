package ru.gb.springbase.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoDataFoundException.class)
    public String handleNoDataFoundException(NoDataFoundException ex, Model model) {
        model.addAttribute("exception", ex);
        return "404";
    }

}
