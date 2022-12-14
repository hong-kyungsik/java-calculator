package bigDecimalCalculator;

import calculator.Sign;
import calculator.expression.Operand;
import calculator.expression.Operator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalDivideOperator extends Operator<BigDecimal> {
    public BigDecimalDivideOperator(){
        super(Sign.DIVIDE);
    }

    @Override
    public Operand<BigDecimal> calculate(Operand<BigDecimal> left, Operand<BigDecimal> right) {
        if(right==null) return left;
        if(right.getValue().equals(BigDecimal.ZERO)) throw new IllegalArgumentException();
        return new BigDecimalOperand(
                left.getValue().divide(right.getValue(), RoundingMode.HALF_UP),
                left.getFormer(),
                right.getLatter());
    }
}
