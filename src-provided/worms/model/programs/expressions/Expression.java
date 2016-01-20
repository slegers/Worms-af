package worms.model.programs.expressions;

import worms.model.Program;

public abstract class Expression {
	public abstract Object getValue();

	public abstract Object getResult();

	boolean exceeds(double d) {
		return ((d > Double.MAX_VALUE) || (d < -(Double.MAX_VALUE)));
	}

	public abstract Object getResult(Program program);

}
