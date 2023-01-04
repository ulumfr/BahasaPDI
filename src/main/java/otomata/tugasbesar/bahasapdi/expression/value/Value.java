package otomata.tugasbesar.bahasapdi.expression.value;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.expression.Expression;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Value<T> implements Expression {
    @EqualsAndHashCode.Include
    private final T value;

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Value<?> evaluate() {
        return this;
    }
}
