package worms.model.programs.types;

public abstract class Type {
	public abstract boolean equals(Type type);

	public abstract Object getValue();

	public abstract void setValue(Object o);
}
