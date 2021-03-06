package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.types.DoubleType;

public class GetMaxAP extends Expression {
	private EntityLiteral<Worm> value;

	@SuppressWarnings("unchecked")
	public GetMaxAP(Expression e) {
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
		return new DoubleLiteral(new DoubleType(getWorm().getMaxActionPoints()));
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
