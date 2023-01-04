package otomata.tugasbesar.bahasapdi.statement.loop;

import otomata.tugasbesar.bahasapdi.context.BreakContext;
import otomata.tugasbesar.bahasapdi.statement.Statement;

public class BreakStatement implements Statement {
    @Override
    public void execute() {
        BreakContext.getScope().invoke();
    }
}
