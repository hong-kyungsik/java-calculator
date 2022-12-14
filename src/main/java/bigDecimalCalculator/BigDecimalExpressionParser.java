package bigDecimalCalculator;

import calculator.Sign;
import calculator.expression.Operand;
import calculator.expression.Operator;
import calculator.parser.ExpressionParser;

import java.math.BigDecimal;

public class BigDecimalExpressionParser extends ExpressionParser<BigDecimal> {

    @Override
    protected BigDecimal fromString(String str) {
        try{
            return new BigDecimal(str);
        }catch (NumberFormatException e){
            throw new IllegalStateException();
        }
    }

    @Override
    protected Operand<BigDecimal> operandFromValue(BigDecimal value) {
        return new BigDecimalOperand(value);
    }

    @Override
    protected Operator<BigDecimal> operatorFromSign(Sign sign) {
        switch(sign){
            case ADD:
                return new BigDecimalAddOperator();
            case SUBTRACT:
                return new BigDecimalSubtractOperator();
            case MULTIPLY:
                return new BigDecimalMultiplyOperator();
            case DIVIDE:
                return new BigDecimalDivideOperator();
            default:
                throw new IllegalArgumentException();
        }
    }
}
