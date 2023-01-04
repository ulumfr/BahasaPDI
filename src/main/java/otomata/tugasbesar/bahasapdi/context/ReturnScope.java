package otomata.tugasbesar.bahasapdi.context;

import lombok.Getter;
import otomata.tugasbesar.bahasapdi.expression.value.Value;

@Getter
public class ReturnScope {
	private boolean invoked;
	private Value<?> result;

	public void invoke(Value<?> result) {
		setInvoked(true);
		setResult(result);
	}

	private void setInvoked(boolean invoked) {
		this.invoked = invoked;
	}

	private void setResult(Value<?> result) {
		this.result = result;
	}
}