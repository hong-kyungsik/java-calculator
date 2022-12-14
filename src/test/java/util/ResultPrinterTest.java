package util;

import bigDecimalCalculator.BigDecimalOperand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultPrinterTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("결과를 정상적으로 출력한다.")
    void printTest(){
        ResultPrinter.print(new BigDecimalOperand(new BigDecimal("3829.34729")));
        assertEquals("결과 : 3829.34729", outputStreamCaptor.toString().trim());
    }
}