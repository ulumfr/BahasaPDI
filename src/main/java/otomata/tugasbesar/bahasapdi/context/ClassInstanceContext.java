package otomata.tugasbesar.bahasapdi.context;

import java.util.Stack;

import otomata.tugasbesar.bahasapdi.expression.value.ClassValue;
import otomata.tugasbesar.bahasapdi.expression.value.ThisValue;

/**
 * Holds class instances at the current point of execution
 *
 * @see ThisValue#getValue()
 * @see ThisValue#getValue()
 */
public class ClassInstanceContext {
    private static final Stack<ClassValue> values = new Stack<>();

    public static ClassValue getValue() {
        return values.peek();
    }

    public static void pushValue(ClassValue instance) {
        values.push(instance);
    }

    public static void popValue() {
        values.pop();
    }
}
