package otomata.tugasbesar.bahasapdi.expression.operator;

import static otomata.tugasbesar.bahasapdi.expression.value.NullValue.NULL_INSTANCE;

import java.util.Objects;

import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.value.LogicalValue;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

public class EqualsOperator extends BinaryOperatorExpression {
    public EqualsOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();
        Value<?> right = getRight().evaluate();
        boolean result;
        if (left == NULL_INSTANCE || right == NULL_INSTANCE) {
            result = left == right;
        } else if (Objects.equals(left.getClass(), right.getClass())) {
            result = left.getValue().equals(right.getValue());
        } else {
            result = left.toString().equals(right.toString());
        }
        return new LogicalValue(result);
    }
}
