package bigDecimalCalculator;

import calculator.expression.Operand;
import calculator.expression.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalDivideOperatorTest {

    @Test
    @DisplayName("BigDecimal을 정상적으로 나눈다.")
    void divideBigDecimalsTest(){
        divideBigDecimalTest(
            new BigDecimal("2.4"),
            new BigDecimal("2.0"),
            new BigDecimal("1.2")
        );
    }

    @Test
    @DisplayName("0으로 나누는 경우 예외가 발생한다.")
    void divideBigDecimalsFailWhenDivideZeroTest(){
        assertThrows(IllegalArgumentException.class, ()-> divideBigDecimalTest(
            new BigDecimal("2.4"),
            new BigDecimal("0"),
            new BigDecimal("1.2")
        ));
    }

    private void divideBigDecimalTest(
            BigDecimal leftBigDecimal,
            BigDecimal rightBigDecimal,
            BigDecimal resultBigDecimal
    ){
        //given
        Operator<BigDecimal> leftOperatorForTest = new BigDecimalDivideOperator();
        Operator<BigDecimal> rightOperatorForTest = new BigDecimalDivideOperator();

        //when
        Operand<BigDecimal> left = new BigDecimalOperand(leftBigDecimal);
        left.connectFormer(leftOperatorForTest);

        Operand<BigDecimal> right = new BigDecimalOperand(rightBigDecimal);
        right.connectLatter(rightOperatorForTest);

        Operator<BigDecimal> divideOperator = new BigDecimalDivideOperator();
        divideOperator.connectFormer(left);
        divideOperator.connectLatter(right);

        //then
        Operand<BigDecimal> result = divideOperator.calculate(left, right);
        assertEquals(resultBigDecimal, result.getValue());
        assertEquals(leftOperatorForTest, result.getFormer());
        assertEquals(rightOperatorForTest, result.getLatter());
    }
}