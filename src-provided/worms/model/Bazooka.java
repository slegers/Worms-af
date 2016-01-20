package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A Class that represents a worm's bazooka. This class is a subclass of the class weapon.
 *
 *	@author		Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * 	@version	3.0
 *
 * https://github.com/hazzarux/worms.git
 */
public class Bazooka extends Weapon {

	/******************************************************************************************************
	 * INSTANCE VARIABLES
	 *****************************************************************************************************/
	/**
	 * Variable registering the lower bound force working on this bazooka's projectile.
	 */
	private static double lowerBound;
	/**
	 * Variable registering the upper bound force working on this bazooka's projectile.
	 */
	private static double upperBound;

	/******************************************************************************************************
	 * CONSTRUCTOR
	 *****************************************************************************************************/
	/**
	 * Initialize this new Bazooka with its firing worm, world, name, mass, damage points, shooting cost,
	 * 	the lower and upper bound of the force working on the projectile.
	 * @param	worm
	 * 			The worm that holds this bazooka.
	 * @param 	world
	 * 			The world that displays this bazooka.
	 * @post	| super.getFiringWorm() == worm
	 * @post	| super.getWorld() == world
	 * @post	| super.getName() == this.getName()
	 * @post 	| super.getMass() == this.getMass()
	 * @post	| super.getHitPoints() == this.getHitPoints()
	 * @post 	| super.getShootingCost() == this.getShootingCost(()
	 * @post 	| new.getLowerBound() == this.getLowerBound()
	 * @post	| new.getUpperBound() == this.getUpperBound()
	 */
	@Raw
	public Bazooka(Worm worm, World world) {
		super.setFiringWorm(worm);
		super.setWorld(world);
		super.setName(getName());
		super.setMass(getMass());
		super.setHitPoints(getHitPoints());
		super.setShootingCost(getShootingCost());
		setLowerBound(2.5);
		setUpperBound(9.5);
	}

	/******************************************************************************************************
	 * CONSTANTS
	 *****************************************************************************************************/
	/**
	 * Return the hit points of this Bazooka.
	 */
	@Override
	@Basic
	@Raw
	@Immutable
	public int getHitPoints() {
		return 80;
	}

	/**
	 * Return the shooting cost of this Bazooka.
	 */
	@Override
	@Basic
	@Raw
	@Immutable
	public int getShootingCost() {
		return 50;
	}

	/**
	 * Return the mass of this Bazooka.
	 */
	@Override
	@Basic
	@Raw
	@Immutable
	public double getMass() {
		return 0.300;
	}

	/******************************************************************************************************
	 * GETTERS & SETTERS
	 *****************************************************************************************************/
	/**
	 * Return the lower bound of the force working on this bazooka.
	 */
	@Override
	@Basic
	@Raw
	public double getLowerBound() {
		return lowerBound;
	}

	/**
	 * Return the name of this Bazooka.
	 */
	@Override
	@Basic
	@Raw
	public String getName() {
		return "Bazooka";
	}

	/**
	 * Set the lower bound of the force working on this bazooka.
	 * @param	lowerBound
	 * 			The lower bound force working on this bazooka projectile.
	 * @post	| new.getLowerBound() == lowerBound
	 */
	@Override
	@SuppressWarnings("static-access")
	@Raw
	public void setLowerBound(double lowerBound) {
		Bazooka.lowerBound = lowerBound;
	}

	/**
	 * Return the upper bound of the force working on this bazooka.
	 */
	@Override
	@Basic
	@Raw
	public double getUpperBound() {
		return upperBound;
	}

	/**
	 * Set the upper bound of the force working on this bazooka.
	 * @param	upperBound
	 * 			The upper bound force working on this bazooka projectile
	 * @post	| new.getUpperBound() == upperBound
	 */
	@Override
	@SuppressWarnings("static-access")
	public void setUpperBound(double upperBound) {
		Bazooka.upperBound = upperBound;
	}
}
