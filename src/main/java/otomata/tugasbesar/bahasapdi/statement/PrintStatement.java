package otomata.tugasbesar.bahasapdi.statement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

@RequiredArgsConstructor
@Getter
public class PrintStatement implements Statement {
    private final Expression expression;

    @Override
    public void execute() {
        Value<?> value = expression.evaluate();
        System.out.println(value);
    }
}
