package otomata.tugasbesar.bahasapdi.expression.operator;

import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

public class ClassInstanceOperator extends UnaryOperatorExpression {
    public ClassInstanceOperator(Expression value) {
        super(value);
    }

    @Override
    public Value<?> evaluate() {
        return getValue().evaluate(); // will return toString() value
    }
}

