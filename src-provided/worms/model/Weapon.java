package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class that represents a weapon with a given name, mass, hitpoits, shootingCost, a lower and upper bound propelled force. This is the supperclass of the bazooka and rifle class.
 *
 * @author	Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * @version	3.0
 *
 * https://github.com/hazzarux/worms.git
 */
public class Weapon {

	/******************************************************************************************************
	 * INSTANCE VARIABLES
	 *****************************************************************************************************/

	/**
	 * Variable registering the name of this weapon.
	 */
	private String name;
	/**
	 * Variable registering the mass of this weapon.
	 */
	private double mass;
	/**
	 * Variable registering the hit points of this weapon
	 */
	private int hitPoints;
	/**
	 * Variable registering the shooting cost of this weapon.
	 */
	private int shootingCost;
	/**
	 * Variable referencing to the worm that's shooting.
	 */
	private Worm firingWorm;
	/**
	 * Variable registering the lower bound force working on this weapon.
	 */
	private double lowerBound;
	/**
	 * Variable registering the upper bound force working on this weapon.
	 */
	private double upperBound;
	/**
	 * Variable referencing to the active projectile.
	 */
	private Projectile activeProjectile;
	/**
	 * Variable referencing to the world of this weapon.
	 */
	private World world;

	/******************************************************************************************************
	 *	GETTERS & SETTERS
	 *****************************************************************************************************/

	/**
	 * Return the lower bound of the force working on this weapon.
	 */
	@Basic
	@Raw
	public double getLowerBound() {
		return lowerBound;
	}

	/**
	 * Set the lower bound of the foce working on this weapon.
	 * @param	lowerBound
	 * 			The new lower bound force working on this weapon projectile.
	 * @post	| new.getLowerBound() == lowerBound
	 */
	@Raw
	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * Return the upper bound of the force working on this weapon.
	 */
	@Basic
	@Raw
	public double getUpperBound() {
		return upperBound;
	}

	/**
	 * Set the upper bound of the force working on this weapon.
	 * @param	upperBound
	 * 			The new upper bound force working on this weapon projectile
	 * @post	| new.getUpperBound() == upperBound
	 */
	@Raw
	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

	/**
	 * Return the worm that is firing this weapon.
	 */
	@Basic
	@Raw
	public Worm getFiringWorm() {
		return firingWorm;
	}

	/**
	 * Set the firing worm to the given firing worm.
	 *
	 * @param	firingWorm
	 * 			The worm that is firing this weapon
	 * @post	|new.getFiringWorm() == firingWorm
	 */
	@Raw
	public void setFiringWorm(Worm firingWorm) {
		this.firingWorm = firingWorm;
	}

	/**
	 * Return the name of this weapon.
	 */
	@Basic
	@Raw
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this weapon.
	 * @param 	name
	 * 			The name of this weapon
	 * @post	| new.getName() == name
	 */
	@Raw
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return the mass of this weapon.
	 */
	@Basic
	@Raw
	public double getMass() {
		return mass;
	}

	/**
	 * Set the mass of this weapon.
	 * @param	mass
	 * 			The mass of this weapon.
	 * @post	| new.getMass() == mass
	 */
	@Raw
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * Return the hit points that this weapon causes.
	 */
	@Basic
	@Raw
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * Set the number of hit points of this weapon.
	 * @param	hitPoints
	 * 			The number of damage this weapon causes.
	 * @post	| if(getHitPoints() < 0)
	 * 			|	then new.getHitPoints == 0
	 * 			| else
	 * 			|	 new.getHitPoints() == hitPoints
	 */
	@Raw
	public void setHitPoints(int hitPoints) {
		if (hitPoints > 0) {
			this.hitPoints = hitPoints;
		} else {
			this.hitPoints = 0;
		}
	}

	/**
	 * Return the cost of firing this weapon.
	 */
	@Basic
	@Raw
	public int getShootingCost() {
		return shootingCost;
	}

	/**
	 * Set the cost of firing this weapon.
	 * @param	shootingCost
	 * 			The cost of firing this weapon
	 * @post	| if(getShootingCost() < 0)
	 * 			|	then new.getShootingCost() == 0
	 * 			|  else
	 * 			|	new.getShootingCost() == shootingCost
	 */
	@Raw
	public void setShootingCost(int shootingCost) {
		if (shootingCost > 0) {
			this.shootingCost = shootingCost;
		} else {
			this.shootingCost = 0;
		}

	}

	/**
	 * Return the world of this weapon.
	 * @return	| result == this.getWorld()
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Sets the new world to the given world.
	 * @param	world
	 * 			The new world of this weapon
	 * @post	| new.getWorld() = world
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * Return the active projectile.
	 * @return	| result == getActiveProjectile()
	 */
	@Basic
	@Raw
	public Projectile getActiveProjectile() {
		return activeProjectile;
	}

	/**
	 * Set the new avtiveProjectile to the given active projectile.
	 * @param	activeProjectile
	 * 			The new active projectile of this weapon
	 * @post	| new.getActiveProjectile() == activeProjectile
	 */
	@Raw
	public void setActiveProjectile(Projectile activeProjectile) {
		this.activeProjectile = activeProjectile;
	}
}
