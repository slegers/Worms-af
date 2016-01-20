package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.DoubleType;

public class Sin extends Expression {
	private DoubleLiteral value;

	public Sin(Expression value) {
		this.value = (DoubleLiteral) value;
	}

	@Override
	public DoubleLiteral getValue() {
		return this.value;
	}

	@Override
	public DoubleLiteral getResult() {
		if (Double.isInfinite(value.getResult())) {
			return new DoubleLiteral(new DoubleType(0.0));
		} else {
			return new DoubleLiteral(
					new DoubleType(Math.sin(value.getResult())));
		}
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
