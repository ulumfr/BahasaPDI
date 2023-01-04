package otomata.tugasbesar.bahasapdi.expression.operator;

import static otomata.tugasbesar.bahasapdi.expression.value.NullValue.NULL_INSTANCE;

import java.util.Objects;

import otomata.tugasbesar.bahasapdi.exception.ExecutionException;
import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.value.ComparableValue;
import otomata.tugasbesar.bahasapdi.expression.value.LogicalValue;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

public class GreaterThanOperator extends BinaryOperatorExpression {
    public GreaterThanOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();
        Value<?> right = getRight().evaluate();
        boolean result;
        if (left == NULL_INSTANCE || right == NULL_INSTANCE) {
            throw new ExecutionException(String.format("Unable to perform greater than for NULL values `%s`, '%s'", left, right));
        } else if (Objects.equals(left.getClass(), right.getClass()) && left instanceof ComparableValue) {
            //noinspection unchecked,rawtypes
            result = ((Comparable) left.getValue()).compareTo(right.getValue()) > 0;
        } else {
            result = left.toString().compareTo(right.toString()) > 0;
        }
        return new LogicalValue(result);
    }
}

