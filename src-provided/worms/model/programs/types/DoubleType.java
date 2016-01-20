package worms.model.programs.types;

import worms.util.Util;

public class DoubleType extends Type {
	private Double value;

	public DoubleType(double d) {
		this.value = d;
	}

	public DoubleType() {
		this(0.0);
	}

	@Override
	public boolean equals(Type type) {
		return (type.getValue() instanceof Double)
				&& (Util.fuzzyEquals(getValue(), (Double) type.getValue()));
	}

	@Override
	public Double getValue() {
		return this.value;
	}

	@Override
	public void setValue(Object o) {
		if (!(o instanceof Double)) {
			return;
		} else {
			this.value = (Double) o;
		}
	}

	public int toInt() {
		if (value > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (value < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else if (value < 0) {
			return (int) Math.ceil(value);
		} else {
			return (int) Math.floor(value);
		}
	}

}
