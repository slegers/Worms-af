package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.BooleanType;

public class Inequality extends Expression {
	private Expression e1, e2;

	public Inequality(Expression e1, Expression e2) {
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

	@Override
	public BooleanLiteral getResult() {
		if (e1 instanceof EntityLiteral && e2 instanceof EntityLiteral) {
			return new BooleanLiteral(new BooleanType(
					e1.getValue() != e2.getValue()));
		} else if (e1 instanceof BooleanLiteral && e2 instanceof BooleanLiteral) {
			return new BooleanLiteral(new BooleanType(!e1.getValue().equals(
					e2.getValue())));
		} else if (e1 instanceof DoubleLiteral && e2 instanceof DoubleLiteral) {
			return new BooleanLiteral(new BooleanType(!e1.getValue().equals(
					e2.getValue())));
		} else {
			return new BooleanLiteral(new BooleanType(true));
		}
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
