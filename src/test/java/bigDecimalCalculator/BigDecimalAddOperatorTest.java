package bigDecimalCalculator;

import calculator.expression.Operand;
import calculator.expression.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigDecimalAddOperatorTest {

    @Test
    @DisplayName("BigDecimal을 정상적으로 더한다.")
    void addBigDecimalsTest() {
        addBigDecimalTest(
            new BigDecimal("1.2"),
            new BigDecimal("3.4"),
            new BigDecimal("4.6"));
    }

    private void addBigDecimalTest(
        BigDecimal leftBigDecimal,
        BigDecimal rightBigDecimal,
        BigDecimal resultBigDecimal
    ){
        //given
        Operator<BigDecimal> leftOperatorForTest = new BigDecimalAddOperator();
        Operator<BigDecimal> rightOperatorForTest = new BigDecimalAddOperator();

        //when
        Operand<BigDecimal> left = new BigDecimalOperand(leftBigDecimal);
        left.connectFormer(leftOperatorForTest);

        Operand<BigDecimal> right = new BigDecimalOperand(rightBigDecimal);
        right.connectLatter(rightOperatorForTest);

        Operator<BigDecimal> addOperator = new BigDecimalAddOperator();
        addOperator.connectFormer(left);
        addOperator.connectLatter(right);

        //then
        Operand<BigDecimal> result = addOperator.calculate(left, right);
        assertEquals(resultBigDecimal, result.getValue());
        assertEquals(leftOperatorForTest, result.getFormer());
        assertEquals(rightOperatorForTest, result.getLatter());
    }
}