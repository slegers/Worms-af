package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.DoubleType;

public class Cos extends Expression {
	private DoubleLiteral value;

	public Cos(Expression value) {
		this.value = (DoubleLiteral) value;
	}

	@Override
	public DoubleLiteral getValue() {
		return this.value;
	}

	@Override
	public DoubleLiteral getResult() {
		if (Double.isInfinite(Math.cos(value.getResult()))) {
			return new DoubleLiteral(new DoubleType(0.0));
		} else {
			return new DoubleLiteral(
					new DoubleType(Math.cos(value.getResult())));
		}
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
