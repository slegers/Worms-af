package worms.model.programs.expressions;

import worms.model.Food;
import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.types.DoubleType;

public class GetX extends Expression {

	private Expression value;

	@SuppressWarnings("unchecked")
	public GetX(Expression e) {
		this.value = e;
	}

	@Override
	public Expression getValue() {
		return this.value;
	}

	public DoubleLiteral getResult(Program p) {
		if (value instanceof SelfExpression) {
			return new DoubleLiteral(new DoubleType(p.getWorm()
					.getXCoordinate()));
		}
		if (value.getResult() instanceof Worm) {
			return new DoubleLiteral(new DoubleType(
					((Worm) value.getResult()).getXCoordinate()));
		}
		return new DoubleLiteral(new DoubleType(
				((Food) value.getResult()).getXCoordinate()));
	}

	@Override
	public Object getResult() {
		return null;
	}

}
