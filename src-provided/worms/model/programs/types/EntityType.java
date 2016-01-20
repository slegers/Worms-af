package worms.model.programs.types;

import worms.model.Food;
import worms.model.Worm;

public class EntityType<T> extends Type {
	public EntityType(Worm worm) {
		this.value = (T) worm;
	}

	public EntityType() {

	}

	public EntityType(Food closestFood) {
		this.value = (T) closestFood;
	}

	@Override
	public boolean equals(Type type) {
		return type.getValue().equals(getValue());
	}

	@Override
	public T getValue() {
		return this.value;
	}

	private T value;

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(Object o) {
		if (!(o instanceof Food || o instanceof Worm)) {
			return;
		} else {
			this.value = (T) o;
		}
	}

}
