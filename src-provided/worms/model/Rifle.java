package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A Class that represents a worm his rifle. This class is a subclass of the class weapon.
 *
 *	@author	 Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * 	@version 3.0
 *
 * https://github.com/hazzarux/worms.git
 */
public class Rifle extends Weapon {
	/**
	 *  Initialize this new rifle with its name,mass, damage points, shooting cost, current worm and world.
	 *
	 * @param	worm
	 * 			The worm that has this rifle.
	 * @param	world
	 *			The world that holds this rifle.
	 * @post	| super.getFiringWorm() == worm
	 * @post	| super.getWorld() == world
	 * @post	| super.getName() == this.getName()
	 * @post 	| super.getMass() == this.getMass()
	 * @post	| super.getHitPoints() == this.getHitPoints()
	 * @post    | super.getShootingCost() == this.getShootingCost(()
	 */
	public Rifle(Worm worm, World world) {
		super.setFiringWorm(worm);
		super.setWorld(world);
		super.setName(getName());
		super.setMass(getMass());
		super.setHitPoints(getHitPoints());
		super.setShootingCost(getShootingCost());
	}

	/**
	 * Return the name of this rifle.
	 */
	@Override
	@Basic
	@Raw
	@Immutable
	public String getName() {
		return "The Rifle";
	}

	/**
	 * Return the mass of this rifle.
	 */
	@Override
	@Basic
	@Raw
	@Immutable
	public double getMass() {
		return 0.010;
	}

	/**
	 * Return the hit points of this rifle.
	 */
	@Override
	@Basic
	@Raw
	@Immutable
	public int getHitPoints() {
		return 20;
	}

	/**
	 * Return the shooting cost of this rifle.
	 */
	@Override
	@Basic
	@Raw
	@Immutable
	public int getShootingCost() {
		return 10;
	}
}
