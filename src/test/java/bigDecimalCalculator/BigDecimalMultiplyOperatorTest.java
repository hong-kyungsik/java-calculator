package bigDecimalCalculator;

import calculator.expression.Operand;
import calculator.expression.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalMultiplyOperatorTest {
    @Test
    @DisplayName("BigDecimal을 정상적으로 곱한다.")
    void multiplyBigDecimalsTest() {
        multiplyBigDecimalTest(
                new BigDecimal("1.2"),
                new BigDecimal("3.4"),
                new BigDecimal("4.08"));
    }

    private void multiplyBigDecimalTest(
            BigDecimal leftBigDecimal,
            BigDecimal rightBigDecimal,
            BigDecimal resultBigDecimal
    ){
        //given
        Operator<BigDecimal> leftOperatorForTest = new BigDecimalMultiplyOperator();
        Operator<BigDecimal> rightOperatorForTest = new BigDecimalMultiplyOperator();

        //when
        Operand<BigDecimal> left = new BigDecimalOperand(leftBigDecimal);
        left.connectFormer(leftOperatorForTest);

        Operand<BigDecimal> right = new BigDecimalOperand(rightBigDecimal);
        right.connectLatter(rightOperatorForTest);

        Operator<BigDecimal> multiplyOperator = new BigDecimalMultiplyOperator();
        multiplyOperator.connectFormer(left);
        multiplyOperator.connectLatter(right);

        //then
        Operand<BigDecimal> result = multiplyOperator.calculate(left, right);
        assertEquals(resultBigDecimal, result.getValue());
        assertEquals(leftOperatorForTest, result.getFormer());
        assertEquals(rightOperatorForTest, result.getLatter());
    }
}