package otomata.tugasbesar.bahasapdi.statement.loop;

import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.context.MemoryContext;
import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.VariableExpression;
import otomata.tugasbesar.bahasapdi.expression.operator.AdditionOperator;
import otomata.tugasbesar.bahasapdi.expression.operator.LessThanOperator;
import otomata.tugasbesar.bahasapdi.expression.value.LogicalValue;
import otomata.tugasbesar.bahasapdi.expression.value.NumericValue;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

@RequiredArgsConstructor
public class ForLoopStatement extends AbstractLoopStatement {
    private final VariableExpression variable;
    private final Expression lowerBound;
    private final Expression uppedBound;
    private final Expression step;
    private static final Expression DEFAULT_STEP = new NumericValue(1.0);

    public ForLoopStatement(VariableExpression variable, Expression lowerBound, Expression uppedBound) {
        this(variable, lowerBound, uppedBound, DEFAULT_STEP);
    }

    @Override
    protected void init() {
        MemoryContext.getScope().set(variable.getName(), lowerBound.evaluate());
    }

    @Override
    protected boolean hasNext() {
        LessThanOperator hasNext = new LessThanOperator(variable, uppedBound);
        Value<?> value = hasNext.evaluate();
        return value instanceof LogicalValue && ((LogicalValue) value).getValue();
    }

    @Override
    protected void preIncrement() {
    }

    @Override
    protected void postIncrement() {
        AdditionOperator stepOperator = new AdditionOperator(variable, step);
        MemoryContext.getScope().set(variable.getName(), stepOperator.evaluate());
    }
}
