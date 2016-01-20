package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.DoubleType;

public class Multiplication extends Expression {

	private Expression e1, e2;

	public Multiplication(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Expression[] getValue() {
		Expression[] v = new Expression[2];
		v[0] = e1;
		v[1] = e2;
		return v;
	}

	public DoubleLiteral getResult(Program p) {
		Expression ex1 = (DoubleLiteral) e1;
		Expression ex2 = (DoubleLiteral) e2;
		Double d1 = (Double) ex1.getResult();
		Double d2 = (Double) ex2.getResult();
		if (exceeds(d1 * d2)) {
			return new DoubleLiteral(new DoubleType(0.0));
		} else {
			return new DoubleLiteral(new DoubleType(d1 * d2));
		}
	}

	@Override
	public Object getResult() {
		return null;
	}

}
