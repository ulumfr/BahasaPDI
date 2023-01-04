package otomata.tugasbesar.bahasapdi.expression.operator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.expression.Expression;

@RequiredArgsConstructor
@Getter
public abstract class BinaryOperatorExpression implements OperatorExpression {
    private final Expression left;
    private final Expression right;
}

