package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.BooleanType;

public class LessThan extends Expression {
	private Expression e1, e2;

	public LessThan(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Expression[] getValue() {
		Expression[] d = new Expression[2];
		d[0] = e1;
		d[1] = e2;
		return d;
	}

	public BooleanLiteral getResult(Program p) {
		VariableAccess ex1 = (VariableAccess) e1;
		VariableAccess ex2 = (VariableAccess) e2;
		DoubleLiteral dl1 = (DoubleLiteral) ex1.getResult(p);
		DoubleLiteral dl2 = (DoubleLiteral) ex2.getResult(p);
		Double d1 = dl1.getResult();
		Double d2 = dl2.getResult();
		return new BooleanLiteral(new BooleanType(d1 < d2));
	}

	@Override
	public Object getResult() {
		return null;
	}

}
