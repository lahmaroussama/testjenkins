package com.example.testjenkins.Controller;

import com.example.testjenkins.Service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add/{a}/{b}")
    public int add(@PathVariable int a, @PathVariable int b) {
        return calculatorService.add(a, b);
    }

    @GetMapping("/subtract/{a}/{b}")
    public int subtract(@PathVariable int a, @PathVariable int b) {
        return calculatorService.subtract(a, b);
    }

    @GetMapping("/multiply/{a}/{b}")
    public int multiply(@PathVariable int a, @PathVariable int b) {
        return calculatorService.multiply(a, b);
    }

    @GetMapping("/divide/{a}/{b}")
    public double divide(@PathVariable int a, @PathVariable int b) {
        return calculatorService.divide(a, b);
    }
}
