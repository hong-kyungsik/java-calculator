package bigDecimalCalculator;

import calculator.Sign;
import calculator.expression.Expression;
import calculator.expression.Operand;
import calculator.parser.ExpressionParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigDecimalExpressionParserTest {

    private static final String sentence1 = "2 + 3.4 - 7 * 6 + 5.893";
    private static final String sentence2 = "178.8271 / 38828.2991";

    @Test
    @DisplayName("입력받은 문장을 정상적으로 파싱한다.")
    void testParse() {
        ExpressionParser<BigDecimal> parser = new BigDecimalExpressionParser();
        Expression<BigDecimal> parsed = parser.parse(sentence1);

        Operand<BigDecimal> startOperand = parsed.getStartOperand();
        assertEquals(new BigDecimal("2"), startOperand.getValue());
        assertEquals(Sign.ADD, startOperand.getLatter().getSign());

        Operand<BigDecimal> secondOperand = startOperand.getLatter().getLatter();
        assertEquals(new BigDecimal("3.4"), secondOperand.getValue());
        assertEquals(Sign.SUBTRACT, secondOperand.getLatter().getSign());

        Operand<BigDecimal> thirdOperand = secondOperand.getLatter().getLatter();
        assertEquals(new BigDecimal("7"), thirdOperand.getValue());
        assertEquals(Sign.MULTIPLY, thirdOperand.getLatter().getSign());

        Operand<BigDecimal> fourthOperand = thirdOperand.getLatter().getLatter();
        assertEquals(new BigDecimal("6"), fourthOperand.getValue());
        assertEquals(Sign.ADD, fourthOperand.getLatter().getSign());

        Operand<BigDecimal> fifthOperand = fourthOperand.getLatter().getLatter();
        assertEquals(new BigDecimal("5.893"), fifthOperand.getValue());
    }

    @Test
    @DisplayName("두개의 피연산자가 있는 문장을 정상적으로 파싱한다.")
    void testParseTwoOperands() {
        ExpressionParser<BigDecimal> parser = new BigDecimalExpressionParser();
        Expression<BigDecimal> parsed = parser.parse(sentence2);

        Operand<BigDecimal> startOperand = parsed.getStartOperand();
        assertEquals(new BigDecimal("178.8271"), startOperand.getValue());
        assertEquals(Sign.DIVIDE, startOperand.getLatter().getSign());

        Operand<BigDecimal> secondOperand = startOperand.getLatter().getLatter();
        assertEquals(new BigDecimal("38828.2991"), secondOperand.getValue());
    }
}