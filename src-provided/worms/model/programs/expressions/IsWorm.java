package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.types.BooleanType;

public class IsWorm extends Expression {

	private EntityLiteral<?> value;

	public IsWorm(Expression e) {
		this.value = (EntityLiteral<?>) e;
	}

	@Override
	public EntityLiteral<?> getValue() {
		return this.value;
	}

	@Override
	public BooleanLiteral getResult() {
		return new BooleanLiteral(new BooleanType(getValue().getValue()
				.getValue() instanceof Worm));
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
