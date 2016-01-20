package worms.model.programs.types;

public class BooleanType extends Type {

	public BooleanType(boolean b) {
		this.value = b;
	}

	public BooleanType() {
		this(false);
	}

	@Override
	public boolean equals(Type type) {
		return type.getValue() instanceof Boolean
				&& (((Boolean) type.getValue()).compareTo(this.value) == 0);
	}

	@Override
	public Boolean getValue() {
		return this.value;
	}

	@Override
	public void setValue(Object o) {
		if (!(o instanceof Boolean)) {
			return;
		}
		this.value = (Boolean) o;
	}

	private Boolean value;

}
