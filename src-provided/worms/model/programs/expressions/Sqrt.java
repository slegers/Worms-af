package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.DoubleType;

public class Sqrt extends Expression {
	private Expression value;

	public Sqrt(Expression value) {
		this.value = value;
	}

	@Override
	public Expression getValue() {
		return this.value;
	}

	@Override
	public DoubleLiteral getResult() {
		DoubleLiteral dl = (DoubleLiteral) value;
		Double d = (Double) dl.getResult();

		if (Double.isInfinite(Math.sqrt(d))) {
			return new DoubleLiteral(new DoubleType(0.0));
		} else {
			return new DoubleLiteral(new DoubleType(Math.sqrt(d)));
		}
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
