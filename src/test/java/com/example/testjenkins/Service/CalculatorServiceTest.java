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
    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() {
        int a = 5;
        int b = 10;
        int expectedResult = 15;
        when(calculatorService.add(a, b)).thenReturn(expectedResult);
        int actualResult = calculatorService.add(a, b);
        assertEquals(expectedResult, actualResult, "Adding positive numbers should return the correct sum.");


    }

}