package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.BooleanType;

public class Not extends Expression {
	private Expression value;

	public Not(Expression e) {
		this.value = e;
	}

	@Override
	public Expression getValue() {
		return this.value;
	}

	@Override
	public BooleanLiteral getResult() {
		BooleanLiteral bl = (BooleanLiteral) value.getValue();
		Boolean b = bl.getValue().getValue();
		return new BooleanLiteral(new BooleanType(!b));
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
