package calculator.processor;

import bigDecimalCalculator.BigDecimalAddOperator;
import bigDecimalCalculator.BigDecimalMultiplyOperator;
import bigDecimalCalculator.BigDecimalOperand;
import calculator.expression.Expression;
import calculator.expression.Operand;
import calculator.expression.Operator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArithmeticProcessorTest {
    @Test
    @DisplayName("두개의 피연산자를 정상적으로 연산한다.")
    void processTwoOperandsTest(){
        ArithmeticProcessor<BigDecimal> bigDecimalArithmeticProcessor = new ArithmeticProcessor<>();
        Operand<BigDecimal> firstOperand = new BigDecimalOperand(new BigDecimal("1.2"));
        Operand<BigDecimal> secondOperand = new BigDecimalOperand(new BigDecimal("3.4"));
        Operator<BigDecimal> operator = new BigDecimalAddOperator();

        firstOperand.connectLatter(operator);
        operator.connectFormer(firstOperand);

        operator.connectLatter(secondOperand);
        secondOperand.connectFormer(operator);

        Expression<BigDecimal> expression = new Expression<>(firstOperand);

        Operand<BigDecimal> result = bigDecimalArithmeticProcessor.process(expression);
        assertEquals(new BigDecimal("4.6"), result.getValue());
    }

    @Test
    @DisplayName("세개의 피연산자를 정상적으로 연산한다.")
    void processThreeOperandsTest(){
        ArithmeticProcessor<BigDecimal> bigDecimalArithmeticProcessor = new ArithmeticProcessor<>();
        Operand<BigDecimal> firstOperand = new BigDecimalOperand(new BigDecimal("1.2"));
        Operand<BigDecimal> secondOperand = new BigDecimalOperand(new BigDecimal("3.4"));
        Operand<BigDecimal> thirdOperand = new BigDecimalOperand(new BigDecimal("5.6"));
        Operator<BigDecimal> firstOperator = new BigDecimalAddOperator();
        Operator<BigDecimal> secondOperator = new BigDecimalAddOperator();

        firstOperand.connectLatter(firstOperator);
        firstOperator.connectFormer(firstOperand);

        firstOperator.connectLatter(secondOperand);
        secondOperand.connectFormer(firstOperator);

        secondOperator.connectLatter(thirdOperand);
        thirdOperand.connectFormer(secondOperator);

        Expression<BigDecimal> expression = new Expression<>(firstOperand);

        Operand<BigDecimal> result = bigDecimalArithmeticProcessor.process(expression);
        assertEquals(new BigDecimal("4.6"), result.getValue());
    }

    @Test
    @DisplayName("우선순위가 다른 연산자가 섞여 있는 경우를 정상적으로 연산한다.")
    void processFiveOperandsTest(){
        ArithmeticProcessor<BigDecimal> bigDecimalArithmeticProcessor = new ArithmeticProcessor<>();
        Operand<BigDecimal> firstOperand = new BigDecimalOperand(new BigDecimal("1.2"));
        Operand<BigDecimal> secondOperand = new BigDecimalOperand(new BigDecimal("3.4"));
        Operand<BigDecimal> thirdOperand = new BigDecimalOperand(new BigDecimal("5.6"));
        Operand<BigDecimal> fourthOperand = new BigDecimalOperand(new BigDecimal("7.8"));
        Operand<BigDecimal> fifthOperand = new BigDecimalOperand(new BigDecimal("9.0"));

        Operator<BigDecimal> firstOperator = new BigDecimalAddOperator();
        Operator<BigDecimal> secondOperator = new BigDecimalMultiplyOperator();
        Operator<BigDecimal> thirdOperator = new BigDecimalAddOperator();
        Operator<BigDecimal> fourthOperator = new BigDecimalMultiplyOperator();

        firstOperand.connectLatter(firstOperator);
        secondOperand.connectLatter(secondOperator);
        thirdOperand.connectLatter(thirdOperator);
        fourthOperand.connectLatter(fourthOperator);

        firstOperator.connectFormer(firstOperand);
        secondOperator.connectFormer(secondOperand);
        thirdOperator.connectFormer(thirdOperand);
        fourthOperator.connectFormer(fourthOperand);

        secondOperand.connectFormer(firstOperator);
        thirdOperand.connectFormer(secondOperator);
        fourthOperand.connectFormer(thirdOperator);
        fifthOperand.connectFormer(fourthOperator);

        firstOperator.connectLatter(secondOperand);
        secondOperator.connectLatter(thirdOperand);
        thirdOperator.connectLatter(fourthOperand);
        fourthOperator.connectLatter(fifthOperand);

        Expression<BigDecimal> expression = new Expression<>(firstOperand);

        Operand<BigDecimal> result = bigDecimalArithmeticProcessor.process(expression);
        assertEquals(new BigDecimal("90.44"), result.getValue());
    }
}