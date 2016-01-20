package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.DoubleType;

public class Add extends Expression {

	private Expression e1, e2;

	public Add(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Expression[] getValue() {
		Expression[] e = new Expression[2];
		e[0] = e1;
		e[1] = e2;
		return e;
	}

	@Override
	public DoubleLiteral getResult() {
		Expression ex1 = (DoubleLiteral) e1;
		Expression ex2 = (DoubleLiteral) e2;
		Double d1 = (Double) ex1.getResult();
		Double d2 = (Double) ex2.getResult();
		if (exceeds(d1 + d2)) {
			return new DoubleLiteral(new DoubleType(0.0));
		} else {
			return new DoubleLiteral(new DoubleType(d1 + d2));
		}
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
