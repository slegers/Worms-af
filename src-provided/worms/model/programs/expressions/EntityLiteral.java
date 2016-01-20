package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.EntityType;

public class EntityLiteral<T> extends Expression {

	public EntityLiteral(EntityType<T> entity) {
		this.value = entity;
	}

	@Override
	public EntityType<T> getValue() {
		return this.value;
	}

	private EntityType<T> value;

	@Override
	public T getResult() {
		return this.value.getValue();
	}

	@Override
	public Object getResult(Program program) {
		return null;
	}

}
