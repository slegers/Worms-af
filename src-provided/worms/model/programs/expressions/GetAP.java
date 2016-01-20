package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.types.DoubleType;

public class GetAP extends Expression {
	private EntityLiteral<Worm> value;

	@SuppressWarnings("unchecked")
	public GetAP(Expression e) {
		this.value = (EntityLiteral<Worm>) e;
	}

	@Override
	public EntityLiteral<Worm> getValue() {
		return this.value;
	}

	public Worm getWorm() {
		return getValue().getValue().getValue();
	}

	@Override
	public DoubleLiteral getResult() {
		return new DoubleLiteral(new DoubleType(getWorm().getActionPoints()));
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
