package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.DoubleType;

public class DoubleLiteral extends Expression {
	private DoubleType value;

	public DoubleLiteral() {
		this(new DoubleType(0.0));
	}

	public DoubleLiteral(DoubleType value) {
		this.value = value;
	}

	@Override
	public DoubleType getValue() {
		return this.value;
	}

	@Override
	public Double getResult() {
		return this.value.getValue();
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
