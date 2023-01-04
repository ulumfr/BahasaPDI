package otomata.tugasbesar.bahasapdi.statement.loop;

import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.context.MemoryContext;
import otomata.tugasbesar.bahasapdi.exception.ExecutionException;
import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.VariableExpression;
import otomata.tugasbesar.bahasapdi.expression.value.IterableValue;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

import java.util.Iterator;

@RequiredArgsConstructor
public class IterableLoopStatement extends AbstractLoopStatement {
    private final VariableExpression variableExpression;
    private final Expression iterableExpression;

    private Iterator<Value<?>> iterator;

    @Override
    protected void init() {
        Value<?> value = iterableExpression.evaluate();
        if (!(value instanceof IterableValue))
            throw new ExecutionException(String.format("Unable to loop non IterableValue `%s`", value));
        this.iterator = ((IterableValue<?>) value).iterator();
    }

    @Override
    protected boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    protected void preIncrement() {
        MemoryContext.getScope().set(variableExpression.getName(), iterator.next());
    }

    @Override
    protected void postIncrement() {
    }
}
