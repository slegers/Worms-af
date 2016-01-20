package worms.model.programs.expressions;

import worms.model.Food;
import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.types.EntityType;

public class SearchObj extends Expression {

	private Expression e;
	private Object closestObject;
	private Worm closestWorm;
	private Food closestFood;

	public SearchObj(Expression e) {
		this.e = e;
	}

	@Override
	public Expression getValue() {
		return this.e;
	}

	public double calculateDistance(Worm w1, Worm w2) {
		double d = Math.sqrt(Math.pow(
				w1.getXCoordinate() - w2.getXCoordinate(), 2)
				+ Math.pow(w1.getYCoordinate() - w2.getYCoordinate(), 2));
		return d;
	}

	public EntityType<?> getResult(Program p) {
		closestFood = null;
		closestWorm = null;
		closestObject = null;
		Worm worm = p.getWorm();
		Double angleStepSize = (Double) getValue().getValue();

		for (Worm w : p.getWorm().getWorld().getWorms()) {
			if (w != p.getWorm()) {
				double d = calculateDistance(w, worm);
				if (d <= w.getRadius()) {
					if (closestWorm == null) {
						closestWorm = w;
					} else if (d < calculateDistance(closestWorm, worm)) {
						closestWorm = w;
						continue;
					}
					double orientation = worm.getOrientation();
					double angle = Math.asin((w.getYCoordinate() - worm
							.getYCoordinate()) / d);
					if (((w.getXCoordinate() > worm.getXCoordinate() && w
							.getYCoordinate() > worm.getYCoordinate()))) {
						angle = angle;
					} else if (((w.getXCoordinate() < worm.getXCoordinate() && w
							.getYCoordinate() > worm.getYCoordinate()))) {
						angle = Math.PI - angle;
					} else if (((w.getXCoordinate() < worm.getXCoordinate() && w
							.getYCoordinate() < worm.getYCoordinate()))) {
						angle = Math.PI - angle;
					} else if ((w.getXCoordinate() > worm.getXCoordinate() && w
							.getYCoordinate() < worm.getYCoordinate())) {
						angle = 2 * Math.PI + angle;
					}
					if ((angle + Math.abs(Math.asin(w.getRadius() / d)) >= (orientation + angleStepSize))
							&& (angle - Math.abs(Math.asin(w.getRadius() / d)) <= (orientation + angleStepSize))) {
						if (closestWorm == null) {
							closestWorm = w;
						} else if (d < calculateDistance(closestWorm, worm)) {
							closestWorm = w;
						}
					}
				}
			}
		}

		for (Food f : p.getWorm().getWorld().getFoods()) {
			if (((f.getYCoordinate() - worm.getYCoordinate())
					/ (f.getXCoordinate() - worm.getXCoordinate()) == Math
						.tan(worm.getOrientation() + angleStepSize))) {
				if (closestFood == null) {
					closestFood = f;
				} else if (foodDistance(f, worm) < foodDistance(closestFood,
						worm)) {
					closestFood = f;
				}
			}
		}
		if (closestWorm == null && closestFood == null) {
			return null;
		}

		if (closestWorm == null) {
			return new EntityType<Food>(closestFood);
		}
		if (closestFood == null) {
			return new EntityType<Worm>(closestWorm);
		}
		closestObject = closestWorm;
		if (foodDistance(closestFood, worm) < calculateDistance(closestWorm,
				worm)) {
			closestObject = closestFood;
		}
		if (closestObject.getClass().equals(closestFood.getClass())) {
			return new EntityType<Food>(closestFood);
		} else if (closestObject.getClass().equals(closestWorm.getClass())) {
			return new EntityType<Worm>(closestWorm);
		} else {
			return null;
		}
	}

	public double foodDistance(Food f, Worm w) {
		return Math.sqrt(Math.pow(f.getXCoordinate() - w.getXCoordinate(), 2)
				+ Math.pow(f.getYCoordinate() - w.getYCoordinate(), 2));
	}

	@Override
	public EntityType<?> getResult() {
		return null;
	}
}
