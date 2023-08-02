package com.example.testjenkins.Service;

import com.example.testjenkins.Controller.CalculatorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CalculatorServiceTest {
    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    public void testAdd() {
        int result = calculatorService.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    public void testSubtract() {
        int result = calculatorService.subtract(5, 2);
        assertEquals(3, result);
    }

    @Test
    public void testMultiply() {
        int result = calculatorService.multiply(4, 3);
        assertEquals(12, result);
    }

    @Test
    public void testDivide() {
        double result = calculatorService.divide(10, 2);
        assertEquals(5.0, result, 0.0001); // delta parameter for double comparison
    }

    @Test
    public void testDivideByZero() {
        // Test division by zero, it should throw an IllegalArgumentException
        try {
            calculatorService.divide(10, 0);
        } catch (IllegalArgumentException ex) {
            assertEquals("Cannot divide by zero.", ex.getMessage());
        }
    }


}
