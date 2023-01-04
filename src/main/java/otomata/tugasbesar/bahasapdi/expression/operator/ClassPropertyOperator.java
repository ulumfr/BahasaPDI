package otomata.tugasbesar.bahasapdi.expression.operator;

import otomata.tugasbesar.bahasapdi.exception.ExecutionException;
import otomata.tugasbesar.bahasapdi.expression.AssignExpression;
import otomata.tugasbesar.bahasapdi.expression.Expression;
import otomata.tugasbesar.bahasapdi.expression.FunctionExpression;
import otomata.tugasbesar.bahasapdi.expression.VariableExpression;
import otomata.tugasbesar.bahasapdi.expression.value.ClassValue;
import otomata.tugasbesar.bahasapdi.expression.value.ThisValue;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

public class ClassPropertyOperator extends BinaryOperatorExpression implements AssignExpression {
    public ClassPropertyOperator(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Value<?> evaluate() {
        Value<?> left = getLeft().evaluate();

        // access class's property via this instance
        // this :: class_argument
        if (left instanceof ThisValue) {
            left = ((ThisValue) left).getValue();
        }

        if (left instanceof ClassValue) {
            if (getRight() instanceof VariableExpression) {
                // access class's property
                // new ClassInstance[] :: class_argument
                return ((ClassValue) left).getValue(((VariableExpression) getRight()).getName());
            } else if (getRight() instanceof FunctionExpression) {
                // execute class's function
                // new ClassInstance[] :: class_function []
                return ((FunctionExpression) getRight()).evaluate((ClassValue) left);
            }
        }

        throw new ExecutionException(String.format("Unable to access class's property `%s``", getRight()));
    }

    @Override
    public void assign(Value<?> value) {
        Value<?> left = getLeft().evaluate();

        // access class's property via this instance
        // this :: class_argument
        if (left instanceof ThisValue) {
            left = ((ThisValue) left).getValue();
        }

        if (left instanceof ClassValue && getRight() instanceof VariableExpression) {
            String propertyName = ((VariableExpression) getRight()).getName();
            ((ClassValue) left).setValue(propertyName, value);
        }
    }
}
