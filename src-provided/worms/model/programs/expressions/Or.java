package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.BooleanType;

public class Or extends Expression {
	private Expression e1, e2;

	public Or(Expression e1, Expression e2) {
		this.e1 = (BooleanLiteral) e1;
		this.e2 = (BooleanLiteral) e2;
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
		BooleanLiteral bl1 = (BooleanLiteral) e1.getResult();
		BooleanLiteral bl2 = (BooleanLiteral) e2.getResult();
		Boolean b1 = bl1.getResult();
		Boolean b2 = bl2.getResult();
		return new BooleanLiteral(new BooleanType(b1 || b2));
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
