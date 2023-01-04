package otomata.tugasbesar.bahasapdi.expression;

import otomata.tugasbesar.bahasapdi.expression.value.Value;

public interface AssignExpression {
    void assign(Value<?> value);
}
