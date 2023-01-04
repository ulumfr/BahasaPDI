package otomata.tugasbesar.bahasapdi.context.definition;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import otomata.tugasbesar.bahasapdi.statement.FunctionStatement;

import java.util.List;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FunctionDefinition implements Definition {
    @EqualsAndHashCode.Include
    private final String name;
    private final List<String> arguments;
    private final FunctionStatement statement;
    private final DefinitionScope definitionScope;
}
