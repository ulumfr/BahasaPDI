package otomata.tugasbesar.bahasapdi.expression;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.expression.value.ArrayValue;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ArrayExpression implements Expression {
    private final List<Expression> values;

    @Override
    public Value<?> evaluate() {
        return new ArrayValue(this);
    }
}
