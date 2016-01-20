package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.types.BooleanType;
import worms.model.programs.types.DoubleType;
import worms.model.programs.types.EntityType;
import worms.model.programs.types.Type;

public class VariableAccess extends Expression {
	private String name;

	public VariableAccess(String name) {
		this.name = name;
	}

	private Type type;

	public VariableAccess(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public Object[] getValue() {
		Object[] v = new Object[2];
		v[0] = name;
		v[1] = type;
		return v;
	}

	public Expression getResult(Program program) {
		if (type instanceof EntityType<?>) {
			if (program.getEntities().containsKey(name)) {
				return program.getEntities().get(name);
			} else {
				return new EntityLiteral<>(new EntityType<>());
			}
		} else if (type instanceof DoubleType) {
			if (program.getDoubles().containsKey(name)) {
				return program.getDoubles().get(name);
			} else {
				return new DoubleLiteral(new DoubleType(0.0));
			}
		} else if (type instanceof BooleanType) {
			if (program.getBooleans().containsKey(name)) {
				return program.getBooleans().get(name);
			} else {
				return new BooleanLiteral(new BooleanType(false));
			}
		} else {
			return null;
		}
	}

	@Override
	public Object getResult() {
		return null;
	}
}
