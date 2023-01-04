package otomata.tugasbesar.bahasapdi.expression.value;

import static otomata.tugasbesar.bahasapdi.expression.value.NullValue.NULL_INSTANCE;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import otomata.tugasbesar.bahasapdi.expression.ArrayExpression;
import otomata.tugasbesar.bahasapdi.expression.Expression;

public class ArrayValue extends IterableValue<List<Value<?>>> {
    public ArrayValue(ArrayExpression expression) {
        this(expression.getValues()
                .stream()
                .map(Expression::evaluate)
                .collect(Collectors.toList()));
    }

    public ArrayValue(List<Value<?>> values) {
        super(values);
    }

    public Value<?> getValue(int index) {
        if (getValue().size() > index)
            return getValue().get(index);
        return NULL_INSTANCE;
    }

    public void setValue(int index, Value<?> value) {
        if (getValue().size() > index)
            getValue().set(index, value);
    }

    public void appendValue(Value<?> value) {
        getValue().add(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        //noinspection unchecked
        List<Value<?>> oValue = (List<Value<?>>) o;
        return new HashSet<>(getValue()).containsAll(oValue);
    }

    @Override
    public Iterator<Value<?>> iterator() {
        return getValue().iterator();
    }
}
