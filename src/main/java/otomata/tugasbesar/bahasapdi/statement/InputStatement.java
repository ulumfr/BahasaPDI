package otomata.tugasbesar.bahasapdi.statement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.context.MemoryContext;
import otomata.tugasbesar.bahasapdi.expression.value.LogicalValue;
import otomata.tugasbesar.bahasapdi.expression.value.NumericValue;
import otomata.tugasbesar.bahasapdi.expression.value.TextValue;
import otomata.tugasbesar.bahasapdi.expression.value.Value;
import otomata.tugasbesar.bahasapdi.token.TokenType;

import java.util.function.Supplier;

@RequiredArgsConstructor
@Getter
public class InputStatement implements Statement {
    private final String name;
    private final Supplier<String> consoleSupplier;

    @Override
    public void execute() {
        System.out.printf("enter \"%s\" >>> ", name.replace("_", " "));
        String line = consoleSupplier.get();

        Value<?> value;
        if (line.matches(TokenType.Numeric.getRegex())) {
            value = new NumericValue(Double.parseDouble(line));
        } else if (line.matches(TokenType.Logical.getRegex())) {
            value = new LogicalValue(Boolean.valueOf(line));
        } else {
            value = new TextValue(line);
        }

        MemoryContext.getScope().set(name, value);
    }
}
