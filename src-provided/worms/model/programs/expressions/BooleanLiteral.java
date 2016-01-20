package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.BooleanType;

public class BooleanLiteral extends Expression {

	private BooleanType value;

	public BooleanLiteral(BooleanType value) {
		this.value = value;
	}

	@Override
	public BooleanType getValue() {
		return this.value;
	}

	public Boolean getResult() {
		return this.value.getValue();
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
