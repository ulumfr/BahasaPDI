package otomata.tugasbesar.bahasapdi.expression.operator;

import static otomata.tugasbesar.bahasapdi.expression.value.NullValue.NULL_INSTANCE;

import otomata.tugasbesar.bahasapdi.exception.ExecutionException;
import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.value.NumericValue;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

public class FloorDivisionOperator extends BinaryOperatorExpression {
    public FloorDivisionOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();
        Value<?> right = getRight().evaluate();
        if (left == NULL_INSTANCE || right == NULL_INSTANCE) {
            throw new ExecutionException(String.format("Unable to perform floor division for NULL values `%s`, '%s'", left, right));
        } else if (left instanceof NumericValue && right instanceof NumericValue) {
            return new NumericValue(Math.floor(((NumericValue) left).getValue() / ((NumericValue) right).getValue()));
        } else {
            throw new ExecutionException(String.format("Unable to divide non numeric values `%s` and `%s`", left, right));
        }
    }
}
