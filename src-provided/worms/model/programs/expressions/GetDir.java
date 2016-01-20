package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.Worm;

public class GetDir extends Expression {

	private EntityLiteral<Worm> value;

	@SuppressWarnings("unchecked")
	public GetDir(Expression e) {
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
	public Double getResult() {
		return getWorm().getOrientation();
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
