package otomata.tugasbesar.bahasapdi.statement.loop;

import otomata.tugasbesar.bahasapdi.context.NextContext;
import otomata.tugasbesar.bahasapdi.statement.Statement;

public class NextStatement implements Statement {
    @Override
    public void execute() {
        NextContext.getScope().invoke();
    }
}
