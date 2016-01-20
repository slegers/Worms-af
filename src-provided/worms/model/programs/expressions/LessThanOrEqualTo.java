package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.BooleanType;

public class LessThanOrEqualTo extends Expression {

	private DoubleLiteral e1, e2;

	public LessThanOrEqualTo(Expression e1, Expression e2) {
		this.e1 = (DoubleLiteral) e1;
		this.e2 = (DoubleLiteral) e2;
	}

	@Override
	public DoubleLiteral[] getValue() {
		DoubleLiteral[] d = new DoubleLiteral[2];
		d[0] = e1;
		d[1] = e2;
		return d;
	}

	@Override
	public BooleanLiteral getResult() {
		return new BooleanLiteral(new BooleanType(
				e1.getResult() <= e2.getResult()));
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
