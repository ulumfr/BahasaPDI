package otomata.tugasbesar.bahasapdi.expression.operator;

import otomata.tugasbesar.bahasapdi.exception.ExecutionException;
import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.value.LogicalValue;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

public class NotOperator extends UnaryOperatorExpression {
    public NotOperator(Expression value) {
        super(value);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> value = getValue().evaluate();
        if (value instanceof LogicalValue) {
            return new LogicalValue(!(((LogicalValue) value).getValue()));
        } else {
            throw new ExecutionException(String.format("Unable to perform NOT operator for non logical value `%s`", value));
        }
    }
}

