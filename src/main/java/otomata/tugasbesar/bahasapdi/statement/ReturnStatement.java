package otomata.tugasbesar.bahasapdi.statement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.context.ReturnContext;
import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

@RequiredArgsConstructor
@Getter
public class ReturnStatement implements Statement {
    private final Expression expression;

    @Override
    public void execute() {
        Value<?> result = expression.evaluate();
        ReturnContext.getScope().invoke(result);
    }
}
