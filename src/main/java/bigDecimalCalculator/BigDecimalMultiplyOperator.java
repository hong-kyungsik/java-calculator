package bigDecimalCalculator;

import calculator.Sign;
import calculator.expression.Operand;
import calculator.expression.Operator;

import java.math.BigDecimal;

public class BigDecimalMultiplyOperator extends Operator<BigDecimal> {

    public BigDecimalMultiplyOperator() {
        super(Sign.MULTIPLY);
    }

    @Override
    public Operand<BigDecimal> calculate(Operand<BigDecimal> left, Operand<BigDecimal> right) {
        if(right==null) return left;
        return new BigDecimalOperand(
                left.getValue().multiply(right.getValue()),
                left.getFormer(),
                right.getLatter());
    }
}
