package bigDecimalCalculator;

import calculator.expression.Operand;
import calculator.expression.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalSubtractOperatorTest {
    @Test
    @DisplayName("BigDecimal을 정상적으로 뺀다.")
    void subtractBigDecimalsTest(){
        subtractBigDecimalTest(
            new BigDecimal("2.4"),
            new BigDecimal("2.0"),
            new BigDecimal("0.4")
        );
    }

    private void subtractBigDecimalTest(
            BigDecimal leftBigDecimal,
            BigDecimal rightBigDecimal,
            BigDecimal resultBigDecimal
    ){
        //given
        Operator<BigDecimal> leftOperatorForTest = new BigDecimalSubtractOperator();
        Operator<BigDecimal> rightOperatorForTest = new BigDecimalSubtractOperator();

        //when
        Operand<BigDecimal> left = new BigDecimalOperand(leftBigDecimal);
        left.connectFormer(leftOperatorForTest);

        Operand<BigDecimal> right = new BigDecimalOperand(rightBigDecimal);
        right.connectLatter(rightOperatorForTest);

        Operator<BigDecimal> subtractOperator = new BigDecimalSubtractOperator();
        subtractOperator.connectFormer(left);
        subtractOperator.connectLatter(right);

        //then
        Operand<BigDecimal> result = subtractOperator.calculate(left, right);
        assertEquals(resultBigDecimal, result.getValue());
        assertEquals(leftOperatorForTest, result.getFormer());
        assertEquals(rightOperatorForTest, result.getLatter());
    }
}