package worms.model;

import worms.util.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class involving projectiles. Each projectile has a propulsion yield an orientation, a position,d
 * and a certain damage that the projectile causes.
 *
 *  @invar  A projectile should have a valid orientation.
 *  		| canHaveAsOrientation(getOrientation())
 *  @invar	Before using a weapon's projectile there should be action points available.
 *  		| hasActionPoints()
 *  @invar	This projectile should have a valid propulsion yield.
 *  		| canHaveAsPropulsionYield(getYield())
 *  @author		Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * 	@version 	3.0
 *
 * https://github.com/hazzarux/worms.git
 */
public class Projectile {

	/******************************************************************************************************
	 * INSTANCE VARIABLES
	 *****************************************************************************************************/
	/**
	 *Variable registering the orientation of this projectile.
	 */
	private double orientation;
	/**
	 * Variable referencing to the position of this object.
	 * @invar	| position != null
	 */
	private Position position = new Position();
	/**
	 * Variable registering if the projectile is active.
	 * A projectile is active from launch till impact.
	 */
	private boolean isActive;
	/**
	 * Variable referencing to the weapon of this worm.
	 */
	private Weapon weapon;
	/**
	 *Variable registering the propulsion yield of this projectile.
	 */
	private int propulsionYield;
	/**
	 * Variable referencing to the world this projectile is fired in.
	 */
	private World world;
	/**
	 * Variable referencing to the worm that launches this projectile.
	 */
	private Worm firingWorm;

	/******************************************************************************************************
	 * CONSTANTS
	 *****************************************************************************************************/
	/**
	 * Return the homogeneous density of a projectile.
	 */
	@Basic
	@Raw
	@Immutable
	private static double getDensity() {
		return 7800.0;
	}

	/**
	 * Return the time the exerting force works on the body of a projectile.
	 *
	 * @return	The time the exerting force works on a projectile.
	 * 			| result > 0
	 */
	@Model
	private static double getTime() {
		return 0.5;
	}

	/**
	 * Return the gravity that is working on a projectile.
	 *
	 * @return	The gravity on this projectile.
	 * 			| result > 0
	 */
	@Model
	private static double getGravitationOnEarth() {
		return 9.80665;
	}

	/**
	 * Return the number of steps the jump is checked.
	 */
	@Model
	private static double getTimeStep() {
		return 1e-2;
	}

	/******************************************************************************************************
	 * CONSTRUCTOR
	 *****************************************************************************************************/

	/**
	 *  Initialize this new projectile with a current world, worm, x-coordinate, y-coordinate, orientation, propulsion yield, weapon.
	 * @param 	firingWorm
	 * 			The worm shooting this new projectile.
	 * @param 	world
	 * 			The world witch contain this new projectile
	 * @param 	xCoordinate
	 * 			The x-coordinate for this new projectile.
	 * @param 	yCoordinate
	 * 			The y-coordinate for this new projectile.
	 * @param 	orientation
	 * 			The orientation for this new projectile.
	 * @param 	propulsionYield
	 * 			The propulsionYield of this projectile.
	 * @param 	weapon
	 * 			The weapon for this new projectile.
	 * @post	| new.getFiringWorm() == firingWorm
	 * @post	| new.getWorld() == world
	 * @post	| new.getXCoordinate() == xCoordinate
	 * @post	| new.getYCoordinate() == yCoordinate
	 * @post	| new.getOrientation() == orientation
	 * @post	| new.getYield() == propulsionYield
	 * @post	| new.getWeapon() == weapon
	 */
	@Raw
	public Projectile(Worm firingWorm, World world, double xCoordinate,
			double yCoordinate, double orientation, int propulsionYield,
			Weapon weapon) {
		setFiringWorm(firingWorm);
		setWorld(world);
		setXCoordinate(xCoordinate);
		setYCoordinate(yCoordinate);
		setOrientation(orientation);
		setPropulsionYield(propulsionYield);
		setWeapon(weapon);
	}

	/****************************************************************************************************
	 * GETTERS & SETTERS
	 *****************************************************************************************************/
	/**
	 * Return the world of this projectile.
	 * @return	The world of this projectile.
	 * 			| result == getWorld()
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Set the world of this projectile to the given world.
	 *
	 * @param	world
	 * 			The world in witch this projectile is moving in.
	 * @post	Set the world of this projectile to the given world.
	 * 			| new.getWorld() == world
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	/**
	 * Return the x-coordinate of this projectile.
	 */
	@Basic
	@Raw
	public double getXCoordinate() {
		return getPosition().getXCoordinate();
	}

	/**
	 * Set the x-coordinate of this projectile to the given x-coordinate.
	 * @param	xCoordinate
	 * 			The new x-coordinate of this projectile.
	 * @post	| new.getXCoordinate() == xCoordinate
	 */
	@Raw
	public void setXCoordinate(double xCoordinate) {
		getPosition().setXCoordinate(xCoordinate);
	}

	/**
	 * Return the y-coordinate of this projectile.
	 */
	@Basic
	@Raw
	public double getYCoordinate() {
		return getPosition().getYCoordinate();
	}

	/**
	 * Set the y-coordinate of this projectile to the given y-coordinate.
	 * @param	yCoordinate
	 * 			The new y-coordinate of this projectile.
	 * @post	| new.getYCoordinate() == yCoordinate
	 */
	@Raw
	public void setYCoordinate(double yCoordinate) {
		getPosition().setYCoordinate(yCoordinate);
	}

	/**
	 * Return the radius of this projectile.
	 */
	public double getRadius() {
		return calculateRadius(getDensity(), getWeapon().getMass());
	}

	/**
	 * Return the orientation of this projectile.
	 */
	@Basic
	@Raw
	public double getOrientation() {
		return orientation;
	}

	/**
	 * Set the orientation of this projectile in radian.
	 *
	 * @param	orientation
	 * 			The new direction were this projectile is facing to.
	 * @pre		| canHaveAsOrientation(orientation)
	 * @post	| new.getOrientation() == orientation
	 */
	@Raw
	public void setOrientation(double orientation) {
		// nominal
		assert (canHaveAsOrientation(orientation)) : "Precondition: Orientation should be valid.";
		this.orientation = orientation;
	}

	/**
	 * Return reference to the position for this projectile.
	 */
	@Basic
	@Raw
	public Position getPosition() {
		return position;
	}

	/**
	 * Set the position object for this projectile.
	 * @param	position
	 * 			The new position object for this projectile.
	 * @post	| new.getPosition() == position
	 */
	@Raw
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Return the activeness of this projectile.
	 */
	@Basic
	@Raw
	public boolean getActive() {
		return isActive;
	}

	/**
	 * Set the new active state of this projectile to the given sate.
	 *
	 * @param	isActive
	 * 			The new active state of this projectile
	 * @post	| new.getActive() == isActive
	 */
	@Raw
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Return the time for a potential jump for this worm.
	 *
	 *@param	timesStep
	 * 			The next position of steps were the time is checked.
	 *@effect	| ifWormGetsHit()
	 *@return	| while (canHaveAsFurtherRange(time))
	 * 			|	then	new.timeStep += this.timestep
	 * 			| result == timeS
	 */
	public double getJumpTime(double timeStep) {
		double time = 0;

		while (canHaveAsFurtherRange(time)) {
			time += timeStep;
			ifWormGetsHit();

		}
		return time;
	}

	/**
	 * Deduce the hit points of a worm when it is hit by a projectile
	 *
	 * @post	|	for each worm in the world
	 * 			|		then if(getWorm().getHitsBy(this))
	 * 			|				then deduceHitPoints(getWeapon().getHitpoints())
	 */
	@Model
	private void ifWormGetsHit() {
		for (Worm w : getWorld().getWorms()) {
			if (w.getsHitBy(this)) {
				w.deduceHitPoints(getWeapon().getHitPoints());
			}
		}
	}

	/**
	 * Check if this projectile hits an object.
	 *
	 * @param	time
	 * 			The number of steps were the time is checked.
	 * @return	| result == !getWorld().isImpassable(getJumpStep(time)[0],
	 *			| 				getJumpStep(time)[1], getRadius())
	 *			|					&& !getFiringWorm().getsHitBy(this)
	 *			|					&& getWorld().isInside(getJumpStep(time)[0],
	 *			|					getJumpStep(time)[1])
	 */
	@Model
	private boolean canHaveAsFurtherRange(double time) {
		return !getWorld().isImpassable(getJumpStep(time)[0],
				getJumpStep(time)[1], getRadius())
				&& !getFiringWorm().getsHitBy(this)
				&& getWorld().isInside(getJumpStep(time)[0],
						getJumpStep(time)[1]);
	}

	/**
	 * Return the worm that shoots this projectile.
	 */
	@Basic
	@Raw
	public Worm getFiringWorm() {
		return firingWorm;
	}

	/**
	 * Set the worm that fires this projectile to a given worm.
	 * @param	firingWorm
	 * 			The worm with this projectile
	 * @post	| new.getWorm() == firingWorm
	 */
	@Raw
	public void setFiringWorm(Worm firingWorm) {
		this.firingWorm = firingWorm;
	}

	/**
	 * Return the weapon of this Projectile
	 */
	@Basic
	@Raw
	public Weapon getWeapon() {
		return weapon;
	}

	/**
	 * Set the weapon of this projectile to the given weapon's projectile.
	 * @param	weapon
	 * 			The new weapon of this projectile
	 * @post	| new.getWeapon() == weapon
	 */
	@Raw
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	/**
	 * Return the propulsion yield of this projectile.
	 */
	@Basic
	@Raw
	public int getPropulsionYield() {
		return propulsionYield;
	}

	/**
	 * Set the propulsion yield of this projectile.
	 *
	 * @param	propulsionYield
	 * 			The new propulsion yield of this projectile.
	 * @pre		| canHaveAsPropulsionYield(propulsionYield)
	 * @post	| new.getPropulsionYield() == propulsionYield
	 *
	 */
	@Raw
	public void setPropulsionYield(int propulsionYield) {
		if (canHaveAsPropulsionYield(propulsionYield)) {
			assert (canHaveAsPropulsionYield(propulsionYield)) : "Precondition: the propulsion yield of this projectile should be valid";
		}
		this.propulsionYield = propulsionYield;
	}

	/****************************************************************************************************
	 * VALIDATORS
	 *****************************************************************************************************/

	/**
	 * Check whether the orientation of this projectile is valid.
	 *
	 * @param	orientation
	 * 			The orientation to check.
	 * @return  | result == !(Double.isNan(orientation) && (fuzzyLessThanOrEqualTo(orientation, pi))
	 * 			|   			&& (fuzzyGreaterThanOrEqualTo(orientation, -pi)))
	 */
	private boolean canHaveAsOrientation(double orientation2) {
		return (!Double.isNaN(orientation2)
				&& (Util.fuzzyLessThanOrEqualTo(orientation2, Math.PI)) && (Util
					.fuzzyGreaterThanOrEqualTo(orientation2, -Math.PI)));
	}

	/**
	 * Check if this projectile is active.
	 */
	@Basic
	@Raw
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Check if this projectile still has action points.
	 *
	 * @return	| result == getFiringWorm().getActionPoints() > 0
	 */
	private boolean hasActionPoints() {
		return getFiringWorm().hasActionPoints();
	}

	/**
	 * Check if this projectile can have this propulsion yield.
	 *
	 * @param 	propulsionYield
	 * 			The propulsion yield of this projectiles
	 * @return	| result == (getPropulsionYield() >= 0 && getPropulsionYield() <=100)
	 */
	public boolean canHaveAsPropulsionYield(int propulsionYield) {
		return (propulsionYield >= 0 && propulsionYield <= 100);
	}

	/****************************************************************************************************
	 * HELPER FUNCTIONS
	 *****************************************************************************************************/

	/**
	 * Calculate the radius of this projectile.
	 * @param	density
	 * 			The density of this projectile.
	 * @param 	mass
	 * 			The mass of this projectile.
	 * @throws	OutOfMemoryError
	 * 			There is not enough memory for this function to be executed.
	 * @throws 	StackOverflowError
	 * 		   	This function is not allowed to use too much memory on the call stack.
	 * @return	| result == Math.pow((3 * this.getMass()) / (4 * this.getDensity() * pi), 1.0 / 3.0)
	 */
	@Model
	private double calculateRadius(double density, double mass)
			throws ModelException {
		// defensive
		double radius;
		try {
			radius = Math.pow((3 * mass) / (4 * density * Math.PI), 1.0 / 3.0);
		} catch (StackOverflowError err) {
			throw new ModelException(
					"Stackoverflow happenned calculating mass.");
		} catch (OutOfMemoryError err) {
			throw new ModelException(
					"Out-of-memory error happenned calculating mass.");
		}
		return radius;
	}

	/**
	 * Calculates the initial velocity of this projectile.
	 *
	 * @param 	force
	 * 			The force that is working on this projectile.
	 * @return	| result == (force / getWeapon().getMass())
	 * 			|			* getTime();
	 */
	@Model
	private double calculateInitialVelocity(double force) {
		return (force / getWeapon().getMass()) * Projectile.getTime();

	}

	/****************************************************************************************************
	 *  FUNCTIONS
	 *****************************************************************************************************/
	/**
	 * Return an array containing in-flight positions of this jumping worm at any deltaT seconds after launch.
	 * The First position in this array contains the time according the x-axis
	 * The second position in this array contains the time according the y-axis
	 * @param 	deltaT
	 * 			The seconds after launch.
	 * @throws	ModelException
	 * 			| ! hasActionPoints()
	 * @throws	ModelException
	 * 			| getWorld().isImpassable(getXCoordinate(), getYCoordinate(), getRadius()))
	 * @return	| array[0] == this.getXCoordinate() + (calculateInitialVelocity(calculateForce(getPropulsionYield())) * deltaT)
	 * 			| array[1] == this.getYCoordinate()
				| 				+ ((calculateInitialVelocity(calculateForce(getPropulsionYield())) * deltaT) - ((1.0 / 2.0)
				|					* getProjectile().getGravitationOnEarth() * (Math.pow(deltaT, 2))));
	 *
	 */
	public double[] getJumpStep(double deltaT) {
		// defensive
		if (!this.hasActionPoints()) {
			throw new ModelException("Not enough action points");
		}
		if (getWorld().isImpassable(getXCoordinate(), getYCoordinate(),
				getRadius())) {
			throw new ModelException("Located on impassable terrain.");
		}
		double force = this.calculateForce(getPropulsionYield());
		double initialVelocityX = this.calculateInitialVelocity(force)
				* Math.cos(this.getOrientation());
		double initialVelocityY = this.calculateInitialVelocity(force)
				* Math.sin(this.getOrientation());

		double xDeltaT = this.getXCoordinate() + (initialVelocityX * deltaT);
		double yDeltaT = this.getYCoordinate()
				+ ((initialVelocityY * deltaT) - ((1.0 / 2.0)
						* Projectile.getGravitationOnEarth() * (Math.pow(
						deltaT, 2))));

		double[] array = new double[2];

		array[0] = xDeltaT;
		array[1] = yDeltaT;

		return array;
	}

	/**
	 * Let this projectile Launch.
	 *
	 * @param	timeStep
	 * 			The value that represents the precision of the jump.
	 * @post 	| new.getX() == getJumpTime(timeStep)[0]
	 * @post	| new.getY() == getJumpTime(timeStep)[1]
	 * @post	| new.getActive() == false
	 * @effect	| getFiringWorm().setActiveProjectile(null)
	 * @effect	| getWorld().getProjectiles().remove(this)
	 */
	public void launch(double timeStep) {
		double jTime = getJumpTime(timeStep);
		double x = getJumpStep(jTime)[0];
		double y = getJumpStep(jTime)[1];
		setXCoordinate(x);
		setYCoordinate(y);
		setActive(false);
		getFiringWorm().setActiveProjectile(null);
		getWorld().getProjectiles().remove(this);
	}

	/**
	 * Return the force working on this projectile.
	 *
	 * @param	yield
	 * 			The propulsion yield of this projectile.
	 * @return	| if (getWeapon().getClass() == Rifle.class) {
	 *			|		then result > 0
	 * @return 	| if (getWeapon().getClass() == Bazooka.class)
	 *	  		|	then result == getWeapon().getLowerBound()
	 *			|			+ (yield * ((getWeapon().getUpperBound() - getWeapon()
	 *			|				.getLowerBound()) / 100));
	 */
	@Model
	private double calculateForce(int yield) {
		if (getWeapon().getClass() == Rifle.class) {
			return 1.5;
		} else if (getWeapon().getClass() == Bazooka.class) {
			double force = getWeapon().getLowerBound()
					+ (yield * ((getWeapon().getUpperBound() - getWeapon()
							.getLowerBound()) / 100));
			return force;
		} else {
			throw new ModelException("Unrecognized weapon type.");
		}
	}

}
