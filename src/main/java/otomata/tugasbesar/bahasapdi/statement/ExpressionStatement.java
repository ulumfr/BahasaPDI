package otomata.tugasbesar.bahasapdi.statement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.expression.Expression;

@RequiredArgsConstructor
@Getter
public class ExpressionStatement implements Statement {
    private final Expression expression;

    @Override
    public void execute() {
        expression.evaluate();
    }
}
