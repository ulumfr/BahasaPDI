package otomata.tugasbesar.bahasapdi.expression;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.context.ClassInstanceContext;
import otomata.tugasbesar.bahasapdi.context.MemoryContext;
import otomata.tugasbesar.bahasapdi.context.MemoryScope;
import otomata.tugasbesar.bahasapdi.context.definition.ClassDefinition;
import otomata.tugasbesar.bahasapdi.context.definition.DefinitionContext;
import otomata.tugasbesar.bahasapdi.expression.value.ClassValue;
import otomata.tugasbesar.bahasapdi.expression.value.NullValue;
import otomata.tugasbesar.bahasapdi.expression.value.Value;
import otomata.tugasbesar.bahasapdi.statement.ClassStatement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Getter
public class ClassExpression implements Expression {
    private final String name;
    private final List<Expression> argumentExpressions;

    @Override
    public Value<?> evaluate() {
        //initialize class arguments
        List<Value<?>> values = argumentExpressions.stream().map(Expression::evaluate).collect(Collectors.toList());

        //get class's definition and statement
        ClassDefinition definition = DefinitionContext.getScope().getClass(name);
        ClassStatement classStatement = definition.getStatement();

        //set separate scope
        MemoryScope classScope = new MemoryScope(null);
        MemoryContext.pushScope(classScope);

        try {
            //initialize constructor arguments
            ClassValue classValue = new ClassValue(definition, classScope);
            ClassInstanceContext.pushValue(classValue);
            IntStream.range(0, definition.getArguments().size()).boxed()
                    .forEach(i -> MemoryContext.getScope()
                            .setLocal(definition.getArguments().get(i), values.size() > i ? values.get(i) : NullValue.NULL_INSTANCE));

            //execute function body
            DefinitionContext.pushScope(definition.getDefinitionScope());
            try {
                classStatement.execute();
            } finally {
                DefinitionContext.endScope();
            }

            return classValue;
        } finally {
            MemoryContext.endScope();
            ClassInstanceContext.popValue();
        }
    }
}
