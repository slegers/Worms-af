package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.BooleanType;

public class GreaterThanOrEqualTo extends Expression {
	private DoubleLiteral e1, e2;

	public GreaterThanOrEqualTo(Expression e1, Expression e2) {
		this.e1 = (DoubleLiteral) e1;
		this.e2 = (DoubleLiteral) e2;
	}

	@Override
	public DoubleLiteral[] getValue() {
		DoubleLiteral[] v = new DoubleLiteral[2];
		v[0] = e1;
		v[1] = e2;
		return v;
	}

	@Override
	public BooleanLiteral getResult() {
		return new BooleanLiteral(new BooleanType(
				e1.getResult() >= e2.getResult()));
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
