package worms.model.programs.expressions;

import worms.model.Food;
import worms.model.Program;
import worms.model.programs.types.BooleanType;

public class IsFood extends Expression {
	private EntityLiteral<?> value;

	public IsFood(Expression e) {
		this.value = (EntityLiteral<?>) e;
	}

	@Override
	public EntityLiteral<?> getValue() {
		return this.value;
	}

	@Override
	public BooleanLiteral getResult() {
		return new BooleanLiteral(new BooleanType(getValue().getValue()
				.getValue() instanceof Food));
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}
}
