package worms.model.programs.expressions;

import worms.model.Program;

public class NullLiteral extends Expression {
	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public Object getResult() {
		return null;
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
