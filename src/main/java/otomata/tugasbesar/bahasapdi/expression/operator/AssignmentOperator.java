package otomata.tugasbesar.bahasapdi.expression.operator;

import otomata.tugasbesar.bahasapdi.expression.AssignExpression;
import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

public class AssignmentOperator extends BinaryOperatorExpression {
    public AssignmentOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        if (getLeft() instanceof AssignExpression) {
            Value<?> right = getRight().evaluate();
            ((AssignExpression) getLeft()).assign(right);
            return getLeft().evaluate();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
