package com.example.calculatorapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class CalculatorExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleArithmeticException(ArithmeticException e) {
        return e.getMessage();  // Returns "Division by zero is not allowed."
    }
}
