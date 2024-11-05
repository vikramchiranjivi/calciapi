package com.example.calculatorapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController  // Marks this class as a REST controller
@RequestMapping("/calculator")  // Base URL path for the API
@CrossOrigin(origins = "*")  // Allow all origins for CORS
public class CalculatorController {

    // Addition endpoint
    @GetMapping("/add")
    public double add(@RequestParam double a, @RequestParam double b) {
        return a + b;
    }

    // Subtraction endpoint
    @GetMapping("/subtract")
    public double subtract(@RequestParam double a, @RequestParam double b) {
        return a - b;
    }

    // Multiplication endpoint
    @GetMapping("/multiply")
    public double multiply(@RequestParam double a, @RequestParam double b) {
        return a * b;
    }

    // Division endpoint
    @GetMapping("/divide")
    public double divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }
}
