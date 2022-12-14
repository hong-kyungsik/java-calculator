package bigDecimalCalculator;

import calculator.Sign;
import calculator.expression.Operand;
import calculator.expression.Operator;

import java.math.BigDecimal;

public class BigDecimalAddOperator extends Operator<BigDecimal> {

    public BigDecimalAddOperator() {
        super(Sign.ADD);
    }

    @Override
    public Operand<BigDecimal> calculate(Operand<BigDecimal> left, Operand<BigDecimal> right) {
        if(right==null) return left;
        return new BigDecimalOperand(
                left.getValue().add(right.getValue()),
                left.getFormer(),
                right.getLatter());
    }
}
