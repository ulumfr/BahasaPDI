package otomata.tugasbesar.bahasapdi.expression;

import otomata.tugasbesar.bahasapdi.expression.value.Value;

public interface Expression {
    Value<?> evaluate();
}
