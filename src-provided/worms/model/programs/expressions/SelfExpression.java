package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.types.EntityType;

public class SelfExpression extends Expression {
	private Worm worm;

	public SelfExpression() {
		this(null);
	}

	public SelfExpression(Worm worm) {
		this.worm = worm;
	}

	@Override
	public EntityLiteral<Worm> getValue() {
		return new EntityLiteral<Worm>(new EntityType<Worm>(worm));
	}

	@Override
	public Worm getResult() {
		return worm;
	}

	@Override
	public Expression clone() {
		return new SelfExpression(getResult());
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
