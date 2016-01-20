package worms.model;

import java.util.ArrayList;

import worms.util.Util;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class that represents a worm with a given position, direction, radius, mass and name.
 * A worm's name may change during the game.
 * Each worm has a certain density, a maximum (and current) number of hit and action points.
 *
 * @invar	This worm should have a valid radius.
 * 			| canHaveAsRadius(getRadius())
 * @invar	This worm should have a valid orientation.
 * 			| canHaveAsOrientation(getOrientation())
 * @invar	This worm should have a valid name.
 * 			| canHaveAsName(getName())
 * @invar	Each worm should be alive if you want to play with him.
 * 			| isAlive()
 * @invar	Each worm should be facing upwards if you want to jump.
 * 			|  wormIsFacingDownwards()
 * @invar	Each worm should have enough action points to play with.
 * 			| hasEnoughActionPoints(getActionPoints())
 * @invar	If this worm isn't adjacent, the worm should fall down.
 * 			| canFall()
 * @invar	A worm should have a valid number of action points.
 * 			| canHaveAsNumberOfActionPoints(getActionPoints())
 * @invar	A worm can only jump if this worm is on passable terrain and if this worm still has action points.
 * 			| canJump()
 * @invar	A worm can only move if this worm is on adjacent terrain and still has enought action points.
 * 			| canMove()
 * @invar	A worm can only shoot if this worm is on passable terrain and if this worm still has action points.
 * 			| canShoot()
 * @invar	A worm can only turn if there are still enough action points, the angle for turning is valid.
 * 			| canTurn()
 *
 * @author	Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * @version	3.0
 *
 * https://github.com/hazzarux/worms.git
 */
public class Worm {

	/******************************************************************************************************
	 * INSTANCE VARIABLES
	 *****************************************************************************************************/
	/**
	 * A Collection of all passable positions of the world this worm is displayed in.
	 */
	ArrayList<Position> passablePositions = new ArrayList<>();
	/**
	 * Variable referencing to the next best allowed move of this worm.
	 */
	private Position bestMove = null;
	/**
	 * Variable referencing to the world this worm is playing in.
	 */
	private World world;
	/**
	 *Variable registering the radius of this worm.
	 */
	private double radius;
	/**
	 * Variable registering the orientation of this worm.
	 */
	private double orientation;
	/**
	 * Variable registering the mass of this worm in kilograms.
	 */
	private double mass;
	/**
	 * Variable registering the name of this worm.
	 */
	private String name;
	/**
	 * Variable registering the action points of this worm.
	 */
	private int actionPoints;
	/**
	 * Variable registering the number of hit points of this worm.
	 */
	private int hitPoints;
	/**
	 * Variable referencing to the position of this worm.
	 */
	private Position position = new Position();
	/**
	 * Variable referencing to the team of this worm.
	 */
	private Team team;
	/**
	 * Variable referencing to the active profjectile of this worm when shooting.
	 */
	private Projectile activeProjectile;

	/******************************************************************************************************
	 * CONSTRUCTOR
	 *****************************************************************************************************/
	/**
	 * Initialize a new worm with a given world, xCoordinate, yCoordinate, orientation, radius, name.
	 * @effect	This new worm is initialized with a given world, x-coordinate, y-coordinate, orientation, radius, name and a default value for the program.
	 * 			| this(world, xCoordinate, yCoordinate, orientation, radius, name, null)
	 *
	 */
	@Raw
	public Worm(World world, double xCoordinate, double yCoordinate,
			double orientation, double radius, String name) {
		this(world, xCoordinate, yCoordinate, orientation, radius, name, null);
	}

	/**
	 * Initialize this new worm with a given x-coordinate, given y-coordinate, orientation,radius,name, world.
	 *
	 * @effect	This new worm is initialized with a given x-coordinate, y-coordinate, orientation,
	 * 			radius, name and a default value for this world.
	 * 			| this(null, xCoordinate, yCoordinate, orientation, radius, name)
	 */
	@Raw
	public Worm(double xCoordinate, double yCoordinate, double orientation,
			double radius, String name) {
		this(null, xCoordinate, yCoordinate, orientation, radius, name);

	}

	/**
	 * Initialize this new worm with a given world and a default value for the x-coordinate, y-coordinate, oreintation, radius and name.
	 * @effect	This new worm is initialized with a given world and default values for the x-coordinate, y-coordinate, orientation,
	 * 			radius, name.
	 * 			| this(world, 0.2, 0.2, 0.2, 0.3, "New worm")
	 */
	@Raw
	public Worm(World world) {
		this(world, 0.2, 0.2, 0.2, 0.3, "New worm");
	}

	private Program program = null;

	/**
	 * Initialize this new worm with given world, x-coordinate, y-coordinate, orientation, a radius and a name.
	 *
	 * @param	world
	 * 			The world were this worm is displayed in.
	 * @param 	xCoordinate
	 * 			The x-coordinate of this new worm.
	 * @param 	yCoordinate
	 * 			The y-coordinate of this new worm.
	 * @param	direction
	 * 			The direction of this new worm.
	 * @param 	radius
	 * 			The radius of this new worm.
	 * @param 	name
	 * 			The name of this new worm.
	 * @param	program
	 * 			The program of this new worm.
	 * @effect	The world of this worm is set to the given world
	 * 			| new.getWorld() == world
	 * @effect	The x-coordinate is set to the given x-coordinate.
	 * 			| new.getXCoordinate() == xCoordinate
	 * @effect	The y-coordinate is set to the given y-coordinate.
	 * 			| new.getYCoordinate() == yCoordinate
	 * @effect	The mass of this new worm is calculated with the given radius and density of this new worm.
	 * 			| new.getmass() == calculateMass(getDensity(), radius)
	 * @effect	The maximum action points of the worm is set to the nearest integer of the mass.
	 * 			| getActionPoints() == (int) Math.round(calculateMass(getDensity(), radius))
	 * @effect 	The orientation is set to the given orientation if the given orientation is valid.
	 * 			| getOrientation() == direction
	 * @effect	The team of this worm is initialized to null.
	 * 			| getTeam() == null
	 * @effect	The rifle is added to this wrom's weapons.
	 * 			| getWeapons().add(Rifle(this, getWorld()))
	 * @effect	The bazooka is added to this worm's weapons
	 * 			| getWeapons().add(new Bazooka(this, getWorld()));
	 */
	public Worm(World world, double xCoordinate, double yCoordinate,
			double direction, double radius, String name, Program program) {
		this.setWorld(world);
		this.setXCoordinate(xCoordinate);
		this.setYCoordinate(yCoordinate);
		this.setOrientation(direction);
		this.setRadius(radius);
		this.setName(name);
		this.setActionPoints(getMaxActionPoints());
		this.setHitPoints(getMaxHitPoints());
		this.setTeam(null);

		Bazooka bazooka = new Bazooka(this, getWorld());
		Rifle rifle = new Rifle(this, getWorld());
		getWeapons().add(bazooka);
		getWeapons().add(rifle);
		selectNextWeapon();

		setProgram(program);
	}

	/****************************************************************************************************
	 * CONSTANTS
	 *****************************************************************************************************/
	/**
	 * Return the time the exerting force works on the body of a worm.
	 */
	@Basic
	@Immutable
	public static double getTime() {
		return 0.5;
	}

	/**
	 * Return the gravity that is working on a worm.
	 *
	 * @return	The gravity on this worm.
	 * 			| result > 0
	 */
	@Basic
	@Immutable
	public static double getGravitationOnEarth() {
		return 9.80665;
	}

	/**
	 * Return the homogeneous density of this worm.
	 */
	@Basic
	@Immutable
	public static double getDensity() {
		return 1062.0;
	}

	/**
	 * Return the lowest possible value of this worm's radius.
	 */
	@Basic
	@Immutable
	public static double getMinimalRadius() {
		return 0.25;
	}

	/**
	 * Return the regular expression needed to validate the name of this worm.
	 * The first letter should be an uppercase letter.
	 * The name must be at least 2 characters long.
	 * Names can use letters (both uppercase and lowercase), quotes (both single and double), numbers and spaces.
	 *
	 * @return	The regular expression needed to validate the name
	 * 			| result == "[A-Z]([0-9a-zA-Z'\" ]+)"
	 */
	@Basic
	private static String getRegex() {
		return "[A-Z]([0-9a-zA-Z'\" ]+)";
	}

	/******************************************************************************************************
	 * GETTERS AND SETTERS
	 *****************************************************************************************************/
	/**
	 * Return the x-coordinate of this worm in meters.
	 * The x-coordinate expresses the distance in meters on the x-axis.
	 */
	@Basic
	@Raw
	public double getXCoordinate() {
		return getPosition().getXCoordinate();
	}

	/**
	 * Set the x-coordinate of this worm to the given x-coordinate.
	 * The x-coordinate is expressed in meters.
	 *
	 * @param	xCoordinate
	 * 			The x-coordinate of this worm.
	 * @post	Set the x-coordinate of this worm to the given x-coordinate
	 * 			| new.getX() == xCoordinate
	 */
	@Raw
	private void setXCoordinate(double xCoordinate) {
		getPosition().setXCoordinate(xCoordinate);
	}

	/**
	 * Return the y-coordinate of this worm.
	 * The y-coordinate expresses the distance in meters on the y-axis.
	 */
	@Basic
	@Raw
	public double getYCoordinate() {
		return getPosition().getYCoordinate();
	}

	/**
	 * Set the y-coordinate of this worm to the given y-coordinate.
	 * The y-coordinate is expressed in meters.
	 *
	 * @param 	yCoordinate
	 * 			The y-coordinate of this worm.
	 * @post	Set the y-coordinate of this worm to the given y-coordinate
	 * 			| new.getY() == yCoordinate
	 */
	@Raw
	private void setYCoordinate(double yCoordinate) {
		getPosition().setYCoordinate(yCoordinate);
	}

	/**
	 * Return the orientation of this worm in radian.
	 * The orientation of this worm is the direction were the worm is facing towards.
	 */
	@Basic
	@Raw
	public double getOrientation() {
		return orientation;
	}

	/**
	 * Set the orientation of this worm in radian.
	 *
	 * @param	orientation
	 * 			The direction were this worm is facing to.
	 * @pre		The given orientation must be valid one for this worm.
	 * 			| canHaveAsOrientation(orientation)
	 * @post	The new orientation of this worm is equal to
	 * 			the given orientation.
	 * 			| new.getOrientation() == orientation
	 */
	@Raw
	@Model
	private void setOrientation(double orientation) {
		// nominal
		assert (canHaveAsOrientation(orientation)) : "Precondition: Orientation should be valid.";
		this.orientation = orientation;
	}

	/**
	 * Return the radius of this worm in meters.
	 */
	@Basic
	@Raw
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Set the radius of this worm in meters.
	 *
	 * @param 	radius
	 * 			The radius of this worm.
	 * @throws	ModelException
	 * 			The given radius of this worm must be valid.
	 * 			| canHaveAsRadius(radius))
	 * @post	The new radius of this worm is equal to the given radius.
	 * 			| new.getRadius() == radius
	 */
	@Raw
	public void setRadius(double radius) {
		// defensive
		if (!canHaveAsRadius(radius)) {
			throw new ModelException("Radius must be valid.");
		}
		this.radius = radius;
	}

	/**
	 * Return the mass of this worm in kilograms.
	 *
	 * @return	mass
	 * 			The mass of this worm.
	 * 			| result == calculateMass(getDensity(), getRadius())
	 */
	public double getMass() {
		return calculateMass(getDensity(), getRadius());
	}

	/**
	 * Return the current action points of this worm.
	 */
	@Basic
	@Raw
	public int getActionPoints() {
		return this.actionPoints;
	}

	/**
	 *
	 * Set the current action points of this worm to the given action points.
	 *
	 * @param	actionPoints
	 * 			The new action points of this worm.
	 * @post	If the number number of action points is a negative value
	 * 			the number of action points is set to zero.
	 * 			| if(actionPoints < 0)
	 * 			| 	then new.getActionPoints() == 0
	 * @post	Else If the number of action points is larger than the maximum number of action points,
	 * 			the new number of action points is set to the maximum number of action points.
	 * 			| else if(actionPoints > getMaxActionPoints()
	 * 			|	then new.getActionPoints() == this.getMaxActionPoints()
	 * @post	Else the number number of action points is a non-negative value and isn't larger than the maximum value of the action points
	 * 			the new number of action points is set to the given number of action points
	 * 			| else (actionPoints >= 0 && actionPoints <= getMaxActionPoints())
	 * 			| 	then new.getActionPoints() == actionPoints
	 */
	@Raw
	@Model
	private void setActionPoints(int actionPoints) {
		// total
		if (actionPoints < 0) {
			this.actionPoints = 0;
		} else if (actionPoints > getMaxActionPoints()) {
			this.actionPoints = getMaxActionPoints();
		} else {
			this.actionPoints = actionPoints;
		}
	}

	/**
	 * Set the action points of this worm to the maximal action points.
	 *
	 * @post	Set the action points of this worm to the maximum action points of this worm.
	 * 			| setActionPoints(getMaxActionPoints())
	 */
	public void rejuiceActionPoints() {
		setActionPoints(getMaxActionPoints());
	}

	/**
	 * Return the name of this worm.
	 */
	@Basic
	@Raw
	public String getName() {
		return name;
	}

	/**
	 * Set the name of this worm.
	 *
	 * @param	name
	 * 			The name of this worm.
	 * @throws	ModelException
	 * 			This worm should have a valid name.
	 * 			| ! canHaveAsName(name)
	 * @post 	If and only if the given name is valid
	 * 		 	then the name of this worm is set to the given name.
	 * 		 	| new.getName() = name
	 */
	@Raw
	public void setName(String name) {
		// defensive
		if (!canHaveAsName(name)) {
			throw new ModelException("The name is not valid!");
		}
		this.name = name;
	}

	/**
	 * Set the number of this worm.
	 * @param	hitPoints
	 * 			The number of hit points of this worm
	 * @post	If the number number of hit points is a negative value
	 * 			the number of hit points is set to zero.
	 * 			| if(hitPoints < 0)
	 * 			|	then new.getHitPoints() == 0
	 * @post	If the number of hit points is a non-negative value
	 * 			and the number of action points isn't larger as the maximum number of hit points,
	 * 			the new number of hit points is set to the given number of hit points
	 * 			| if(hitPoints >= 0 && hitPoints <= getMaxHitPoints())
	 * 			| 	then new.getHitPoints() == hitPoints
	 * @post	If the new number of hit points is larger than the maximum hit points,
	 * 			the new number of hit points is set to the maximum hit points.
	 * 			| if(hitPoints > getMaxHitPoints())
	 * 			|	then new.getHitPoints() == getMaxHitPoints()
	 */
	private void setHitPoints(int hitPoints) {
		// total
		if (hitPoints >= 0 && hitPoints <= getMaxActionPoints()) {
			this.hitPoints = hitPoints;
		} else if (hitPoints < 0) {
			this.hitPoints = 0;
		} else if (hitPoints > getMaxActionPoints()) {
			this.hitPoints = getMaxHitPoints();
		}

	}

	/**
	 * Increases the number of hit points by a certain number.
	 *
	 * @post	The number of hit points increases by a certain number.
	 * 			| new.getHitPoints() == this.getHitPoints() + constant
	 */
	public void increaseHitPointsBy() {
		setHitPoints(getHitPoints() + 10);
	}

	/**
	 * Return the number of hit points of this worm.
	 */
	@Raw
	@Basic
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * Return the maximum action points of this worm.
	 */
	@Basic
	@Raw
	public int getMaxActionPoints() {
		return (int) Math.round(calculateMass(getDensity(), getRadius()));
	}

	/**
	 * Return the world of this worm.
	 * @return	The world of this worm
	 * 			| result == getWorld()
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Set the world of this worm to the given world.
	 *
	 * @param	world
	 * 			The world in witch this worm is playing in.
	 * @post	Set the world of this worm to the given world.
	 * 			| new.getWorld() == world
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	/******************************************************************************************************
	 * VALIDATORS
	 *****************************************************************************************************/
	/**
	 * Check whether the radius is valid for this worm.
	 *
	 * @param	radius
	 * 			The radius to check.
	 * @return	True if and only if the radius is greater than or equal to the minimal radius of a worm and if the radius is a number and isn't equal to infinity.
	 * 			| result == radius >= getMinimalRadius() && !Double.isNan(radius) && !Double.isInfinite(radius)
	 */
	@Raw
	public boolean canHaveAsRadius(double radius) {
		return (radius >= getMinimalRadius() && !Double.isNaN(radius) && !Double
				.isInfinite(radius));
	}

	/**
	 * Check whether the orientation of this worm is valid.
	 *
	 * @param	orientation
	 * 			The orientation to check.
	 * @return 	True if and only if the orientation is a number and between -pi and pi.
	 * 			| result == !(Double.isNan(orientation) && (fuzzyLessThanOrEqualTo(orientation, pi)) &&
	 * 			|	(fuzzyGreaterThanOrEqualTo(orientation, -pi)))
	 */
	@Raw
	public boolean canHaveAsOrientation(double orientation) {
		return (!Double.isNaN(orientation)
				&& (Util.fuzzyLessThanOrEqualTo(orientation, Math.PI)) && (Util
					.fuzzyGreaterThanOrEqualTo(orientation, -Math.PI)));
	}

	/**
	 * Check if the given name of this worm is a correct name.
	 *
	 * @param	name
	 * 			The new name of this worm.
	 * @return 	True if and only if the name of this worm
	 * 		 	can be validated by the regular expression returned by getRegex().
	 * 			| result == name.matches(getRegex())
	 */
	@Raw
	public boolean canHaveAsName(String name) {
		return name.matches(getRegex());
	}

	/**
	 * Check if this worm is still alive.
	 *
	 * @return	Return true if and only if the number of hit points is a non-negative value.
	 * 			| hasHitPoints()
	 */
	@Raw
	public boolean isAlive() {

		if (hasHitPoints()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if the number of steps is a valid number of steps for this worm.
	 *
	 * @param	numberOfSteps
	 * 			The number of steps of this worm.
	 * @return	True if and only if the given number of steps of this worm is a strict positive number.
	 * 			| numberOfSteps > 0
	 */
	@Raw
	public boolean canHaveAsNumberOfSteps(int numberOfSteps) {
		return numberOfSteps > 0;
	}

	/**
	 * Check that there are still enough action points for the next move of this worm.
	 *
	 * @param	actionPointsToCheck
	 * 			The action points that are needed for the next move of this worm.
	 * @return 	True if and only if the actionPointsToCheck are smaller or equal to
	 * 		   	the current action points and is a strict positive number.
	 * 			| result == (actionPointsToCheck > 0 && actionPointsToCheck < this.getActionPoints())
	 */
	@Raw
	public boolean hasEnoughActionPoints(int actionPointsToCheck) {
		return actionPointsToCheck > 0
				&& actionPointsToCheck < this.getActionPoints();
	}

	/**
	 * Check if this worm still is able to turn.
	 * @param	angle
	 * 			The number of radians the orientation of this worm increases/decreases.
	 * @pre 	The given angle must be a valid angle.
	 * 			| canHaveAsOrientation(angle)
	 * @pre		The cost for this angle may not be higher than the current action points.
	 * 			| hasEnoughActionPoints(calculateActionPoints(angle))
	 * @pre		The worm still needs the have action points.
	 * 			| hasActionPoints()
	 * @return	True if and only if there are still enough action points, the angle is valid.
	 * 			| result == (isValidOrientation(angle) && hasActionPoints() && hasEnoughActionPoints(calculateActionPoints(angle)))
	 */
	@Raw
	public boolean canTurn(double angle) {
		if (canHaveAsOrientation(angle)
				&& this.hasEnoughActionPoints(calculateActionPoints(angle))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if this worm is facing downwards.
	 *
	 * @return	True if and only if the orientation of this worm is between pi and 2*pi.
	 * 			| result == (getOrientation() < (2*pi)) && (getOrientation() > pi)
	 *
	 */
	public boolean wormIsFacingDownwards() {
		return ((this.getOrientation() < (2 * Math.PI)) && (this
				.getOrientation() > Math.PI));
	}

	/**
	 * Check if this worm still has action points.
	 *
	 * @return	True if and only if the current action points is a strict positive value.
	 * 			| result == getActionPoints() > 0;
	 */
	public boolean hasActionPoints() {
		return this.getActionPoints() > 0;
	}

	/**
	 * Check if this worm still has hit points.
	 *
	 * @return	True if and only if the current hit points is a strict positive value.
	 * 			| result == this.getHittPoints() > 0;
	 */
	@Model
	public boolean hasHitPoints() {
		return this.getHitPoints() > 0;
	}

	/******************************************************************************************************
	 * HELPER FUNCTIONS
	 *****************************************************************************************************/
	/**
	 * Calculate the mass of this worm, derived from the density and the radius.
	 *
	 * @param	density
	 * 			The homogeneous density of this worm.
	 * @param 	radius
	 * 			The radius of this worm.
	 * @throws	StackOverflowError
	 * 			This function is not allowed to use too much memory on the call stack.
	 * @throws 	OutOfMemoryError
	 * 			There is not enough memory for this function to be executed.
	 * @throws	ModelException
	 * 			Throws a ModelException when there is an error calculating the mass.
	 * @return	The calculated mass of this worm.
	 * 			| mass = density*((4/3)*Math.PI*Math.pow(radius, 3))
	 */
	@Model
	private double calculateMass(double density, double radius)
			throws ModelException {
		// defensive
		try {
			mass = density * ((4.0 / 3.0) * Math.PI * Math.pow(radius, 3));
		} catch (StackOverflowError err) {
			throw new ModelException(
					"Stackoverflow happenned calculating mass.");
		} catch (OutOfMemoryError err) {
			throw new ModelException(
					"Out-of-memory error happenned calculating mass.");
		}
		return mass;

	}

	/**
	 * Calculates if there are still enough action points for the next move of this worm.
	 *
	 * @param	angle
	 * 			The number of radians the orientation increases/decreases.
	 * @return	If the angle doesn't equals zero we return the closest integer to:
	 * 			(2*pi)/angle.
	 * 			| if(getOrientation() != 0)
	 *          |	then result = (int) (Math.round((2 * Math.PI) / (angle))
	 * @return 	Else if and only if the given angle equals zero we return zero.
	 * 			| if(getOrientation() == 0)
	 * 		   	|	then return 0;
	 */
	@Model
	private int calculateActionPoints(double angle) {
		// total
		if (Util.fuzzyEquals(angle, 0)) {
			return 0;
		}
		int result = (int) Math.round((2 * Math.PI) / (Math.abs(angle)));
		return result;
	}

	/**
	 * Calculates the initial velocity of this worm.
	 *
	 * @param 	force
	 * 			The force that is working on this worm.
	 * @return	The initial velocity of this worm calculated with the following formula:
	 * 			initialVelocity = (force/mass)*time.
	 * 			| result = (force/getMass())*getTime()
	 */
	@Model
	private double calculateInitialVelocity(double force) {
		double initialVelocity = (force / this.getMass()) * getTime();
		return initialVelocity;
	}

	/**
	 * Calculates the jump distance of this worm.
	 * @param	initialVelocity
	 * 			The initial velocity of this worm.
	 * @return	The jump distance of this worm whit the following formula:
	 *  		((initialVelocity*initialVelocity) * sin(2*Orientation)) /GravitationOnEarth
	 * 	 		| result == ((Math.pow(initialVelocity, 2) * Math.sin(2 *getOrientation()))) / getGravitationOnEarth()
	 */
	@Model
	private double calculateJumpDistance(double initialVelocity) {
		double distance = ((Math.pow(initialVelocity, 2) * Math.sin(2 * this
				.getOrientation()))) / getGravitationOnEarth();
		return distance;

	}

	/**
	 * Calculates the force that is working on this worm.
	 *
	 * @return 	The force working on this worm.
	 * 		   	| result == 5 * this.getActionPoints())
	 * 			|   + (this.getMass() * getGravitationOnEarth());
	 */
	@Model
	private double calculateForce() {
		double force = (5 * this.getActionPoints())
				+ (this.getMass() * getGravitationOnEarth());
		return force;

	}

	/**
	 * Calculates the potential jump time of this worm.
	 * @param 	jumpDistance
	 * 			The distance this worm jumps across the field.
	 * @param 	initialVelocity
	 * 			the initial velocity that this has before jumping.
	 * @return	the jump time of this worm is calculated by the given formula:
	 * 			jumpDistance/ ((initialVelocity * cos(orientation of this worm)
	 * 			| jumpDistance /
	 * 			| 	(initialVelocity * Math.cos(getOrientation()))
	 */
	@Model
	private double calculateJumpTime(double jumpDistance, double initialVelocity) {
		// total
		if (Util.fuzzyEquals(this.getOrientation(), 0)) { // angle can't be zero
			return 0;
		}
		double time = jumpDistance
				/ (initialVelocity * Math.cos(this.getOrientation()));
		return time;
	}

	/**
	 * Sets the action points of this worm to zero.
	 *
	 * @return	Sets the action points of this worm to zero.
	 *		  	| getActionpoints() == 0
	 */
	@Model
	private void useAllActionPoints() {
		this.setActionPoints(0);
	}

	/******************************************************************************************************
	 * FUNCTIONS
	 *****************************************************************************************************/
	/**
	 * Changes the orientation of this worm
	 * @param 	angle
	 * 			The number of radians the orientation increases/decreases.
	 * @pre		This worm must be able to turn.
	 * 			| canTurn(angle)
	 * @pre 	This worm still needs to have enough action points for turning.
	 * 			| hasEnoughActionPoints(calculateActionPoints(angle))
	 * @effect	The orientation of this worm changes.
	 * 		 	| new.getOrientation() == this.getOrientation() + angle
	 * @effect	The current number of action points of this worm changes.
	 * 			| new.getActionPoints() == this.getActionPoints() - calculateActionPoints(angle)
	 */
	public void turn(double angle) {
		// nominal
		if (!canTurn(angle)) {
			assert (canHaveAsOrientation(angle)) : "Precondition: angle should be valid!";
			assert (hasEnoughActionPoints(calculateActionPoints(angle))) : "Not enough action points!";
			return;
		}
		this.setOrientation(this.getOrientation() + angle);
		this.setActionPoints(this.getActionPoints()
				- this.calculateActionPoints(angle));
	}

	/**
	 * Return an array containing in-flight positions of this jumping worm at any deltaT seconds after launch.
	 * @param 	deltaT
	 * 			The seconds after launch.
	 * @throws	ModelException
	 * 			This worm should have at enough action point for jumpStep to be able to run.
	 * 			| ! hasActionPoints()
	 * @effect	Calculates the force that is working on this worm.
	 * 		   	| result == calculateForce()
	 * @effect	The initial velocity of the x-axis of this worm is calculated.
	 * 			| result == calculateInitialVelocity(calculateForce())
	 *			| * Math.cos(this.getOrientation())
	 * @effect	The initial velocity of the y-axis of this worm is calculated.
	 *			| result == calculateInitialVelocity(calculateForce())
	 *			* Math.sin(this.getOrientation())
	 * @effect	The x-coordinate after this jump.
	 * 			| result == (this.getX() + (calculateInitialVelocity(calculateForce())
	 *			| * Math.sin(this.getOrientation()) * deltaT))
	 * @effect	The y-coordinate after this jump
	 * 			| (this.calculateInitialVelocity(calculateForce())
	 *			| * Math.sin(this.getOrientation()))
	 * 			| ! hasActionPoints()
	 * @return	array
	 * 			Array containing in-flight position x_deltaT and y_deltaT
	 * 			| result == this.getXCoordinate() + (this.calculateInitialVelocity(calculateForce())
	 *			|				 Math.cos(this.getOrientation()) * deltaT);
	 * 			| result ==   this.getYCoordinate()
	 *			|					+ ((this.calculateInitialVelocity(calculateForce())
	 * 			|						 Math.sin(this.getOrientation()) * deltaT) - ((1.0 / 2.0)
	 *			|							* this.getGravitationOnEarth() * (Math.pow(deltaT, 2))))
	 */
	@SuppressWarnings("static-access")
	public double[] jumpStep(double deltaT) {
		// defensive
		if (!this.hasActionPoints()) {
			throw new ModelException("Not enough action points");
		}
		double force = this.calculateForce();
		double initialVelocityX = this.calculateInitialVelocity(force)
				* Math.cos(this.getOrientation());
		double initialVelocityY = this.calculateInitialVelocity(force)
				* Math.sin(this.getOrientation());

		double xDeltaT = this.getXCoordinate() + (initialVelocityX * deltaT);
		double yDeltaT = this.getYCoordinate()
				+ ((initialVelocityY * deltaT) - ((1.0 / 2.0)
						* this.getGravitationOnEarth() * (Math.pow(deltaT, 2))));

		double[] array = new double[2];

		array[0] = xDeltaT;
		array[1] = yDeltaT;

		return array;
	}

	/**
	 * Return the position object for this worm.
	 *
	 * @return	Return the position object for this worm
	 * 			| result == this.getPosition()
	 */
	private Position getPosition() {
		return position;
	}

	/**
	 * Set the new position object for this worm to the given position object.
	 * @param	position
	 * 			The new position object for this worm.
	 * @post	| new.getPosition() == position
	 */
	private void setPosition(Position position) {
		this.position = position;
	}

	/**
	 *	Causes a fall of this worm.
	 *
	 * @pre		This worm must be able to fall.
	 * 			| canfall()
	 * @effect	The y-coordinate that this worm has because of falling is calculated and set to the new y-coordinate.
	 * 			| while( !canHaveAsFurtherFallingDistance())
	 * 			|	then	new.getYCoordinate() == new.getYCoordinate() + this.getYCoordinate()
	 * @effect	There is a check if the worm hits a food object.
	 * 			| checkIfWormCanEat()
	 * @effect	The number of hitPoints is decreased with the cost of falling down.
	 * 			| new.getHitPoints() == this.getHitPoints() - (int) (3*  Math.floor(Math.abs(this.getYCoordinate() - new.getYCoordinate() -this.getRadiusStepSize())))))
	 */
	public void fall() {
		if (!canFall()) {
			throw new ModelException("This worm cannot fall.");
		}
		double originalY = getYCoordinate();
		double yCopy = originalY;
		while (canHaveAsFurtherFallingDistance()) {
			yCopy -= getRadiusStepSize();
		}
		setYCoordinate(yCopy);
		double metersTravelled = Math.floor(Math.abs(originalY - yCopy));
		int cost = (int) (3 * metersTravelled);
		checkIfWormCanEat();
		setHitPoints(getHitPoints() - cost);
	}

	/**
	 * Check if the worm is able to fall further downwards.
	 *
	 * @return	True if and only if the worm isn't on adjacent terrain.
	 * 			| getWorld().isAdjacent(getXCoordinate(), getYCoordinate(), getRadius())
	 */
	@Model
	private boolean canHaveAsFurtherFallingDistance() {
		return getWorld().isAdjacent(getXCoordinate(), getYCoordinate(),
				getRadius());
	}

	/**
	 * Return the time for a potential jump for this worm.
	 *
	 * @param	timesStep
	 * 			The time step size
	 * @return	The time for a potention jump for this worm.
	 * 			while the worm is on adjacent terrain the the time increases with the given timeStep
	 * 			While the worm isn't on impassable and adjacent terrain the time increases with the given timeStep.
	 * 			| while(canJumpFurther_Adjacent(timeStep))
	 * 			|		then result += timeStep
	 * 			| while(canJumpFurther_impassableTerreain_Adjacent(timeStep))
	 *			|	then result += timeStep
	 */
	public double jumpTime(double timeStep) {
		double time = 0;

		while (canJumpFurther_Adjacent(time)) {
			time += timeStep;
		}

		while (canJumpFurther_impassableTerreain_Adjacent(time)) {
			time += timeStep;
		}
		return time;
	}

	/**
	 * Check if the worm is able to jump further on.
	 * @param	time
	 * 			The time step size
	 * @return	True if and only if the worm is on adjacent terrain.
	 * 			| result == getWorld().isAdjacent(jumpStep(time)[0], jumpStep(time)[1],
	 *			|				getRadius());
	 */
	private boolean canJumpFurther_Adjacent(double time) {
		return getWorld().isAdjacent(jumpStep(time)[0], jumpStep(time)[1],
				getRadius());
	}

	/**
	 * Check if the world isn't on
	 * @param	time
	 * 			The time step size
	 * @return	True if and only if the worm isn't on adjacent terrain and on impassable terrain.
	 * 			| result == !getWorld().isImpassable(jumpStep(time)[0], jumpStep(time)[1],
	 *			| 				getRadius())
	 *			|				&& !getWorld().isAdjacent(jumpStep(time)[0], jumpStep(time)[1],
	 *			|					getRadius())
	 */
	private boolean canJumpFurther_impassableTerreain_Adjacent(double time) {
		return (!getWorld().isImpassable(jumpStep(time)[0], jumpStep(time)[1],
				getRadius()) && !getWorld().isAdjacent(jumpStep(time)[0],
				jumpStep(time)[1], getRadius()));
	}

	/**
	 * Return the maximum hit points of this worm.
	 */
	@Basic
	@Raw
	public int getMaxHitPoints() {
		return (int) Math.round(calculateMass(getDensity(), getRadius()));
	}

	/**
	 * Variable referencing to the selectedWeapon of this worm.
	 * @invar	Each worm his selectedWeapon is initialized with the default value.
	 * 			| selectedWeapon == null
	 */
	private Weapon selectedWeapon = null;

	/**
	 * Return the name of the selected weapon of this worm.
	 * @return	If there are no weapons selected the name of this weapon is "No weapon".
	 * 			| if(this.getSelectedWeapon() == null)
	 * 			| 	then result == "No weapon"
	 * @return	If the weapon of this worm isn't a null-pointer the name of this weapon is returned.
	 * 			| if(!this.getSelectedWeapon() == null)
	 * 			|	then result == getSelectedWeapon.getName()
	 */
	public String getSelectedWeapon() {
		if (selectedWeapon == null) {
			return "No weapon";
		} else {
			return selectedWeapon.getName();
		}
	}

	/**
	 * Return the name of this worm's team.
	 *
	 * @return	If the worm isn't added to a team, "No team" is returned.
	 * 			| if(this.getTeam() == null)
	 * 			|	then result == "No team"
	 * @return	If the worm is added to a team, the name of the team is returned.
	 * 			| result == getTeam().getName()
	 */
	public String getTeamName() {
		if (getTeam() == null) {
			return "No team";
		} else {
			return getTeam().getName();
		}
	}

	/**
	 * Check if this worm is able to jump.
	 *
	 * @return	True if and only if this worm still has action points, isn't at impassable terrain.
	 * 			| result == (this.hasActionPoints() && !(this.getWorld().isImpassable(getXCoordinate(), this.getYCoordinate(),
				|			this.getRadius())))
	 */
	public boolean canJump() {
		return (hasActionPoints() && !getWorld().isImpassable(getXCoordinate(),
				getYCoordinate(), getRadius()));
	}

	/**
	 * Let this worm execute a jump.
	 *
	 * @param	timeStep
	 * 			The value that represents the precision of the jump.
	 * @throws 	ModelException
	 * 			This worm should be able to jump.
	 * 			| ! canjump()
	 * @effect	Calculates the jumptime of the x-coordinate of this worm.
	 * 		   	| jumpTime(timeStep)[0]
	 * @effect	Calculates the jumptime of the y-coordinate of this worm.
	 * 			| jumpStep(jTime)[1];
	 * @effect	The actoin points of this worm is set to zero.
	 * 			| useAllActionPoints()
	 * @effect	There is a check if the worm is able to eat something.
	 * 			| checkIfWormCanEat()
	 * @post	The x-coordinate is set to the calculated x-coordinate
	 * 			| new.getXCoordinate == jumpStep(jTime)[0]
	 * @post	The y-coordinate is set to the calculated y-coordinate
	 * 			| new.getYCoordinate == jumpStep(jTime)[1]
	 */
	public void jump(double timeStep) {
		if (!canJump()) {
			throw new ModelException("This worm cannot jump.");
		}
		double jTime = jumpTime(timeStep);
		double x = jumpStep(jTime)[0];
		double y = jumpStep(jTime)[1];
		useAllActionPoints();
		setXCoordinate(x);
		setYCoordinate(y);
		checkIfWormCanEat();
	}

	/**
	 * Return the value of that should be added or subtracted from this worm orientation to check the valid move positions.
	 */
	@Basic
	@Immutable
	private static double getThetaVariation() {
		return 0.7875;
	}

	/**
	 * Return the precision with the divergences may be sampled.
	 */
	@Basic
	@Immutable
	private static double getOrientationStepSize() {
		return 0.0175;
	}

	/**
	 * A collection of all possible positions the worm can move to.
	 *
	 * @return	a Collection of all allowe dMovePositions
	 * 			|	for each moment it's smaller then this worms radius
	 * 			|		for (double getOrientation() - getThetaVariation() = getOrientation() - getThetaVariation(); getOrientation() - getThetaVariation() <= getOrientation()
	 *			|				+ getThetaVariation(); getOrientation() - getThetaVariation() += getOrientationStepSize())
	 *			|			if(getWorld().isAdjacentAux(getX() + i * Math.cos(getOrientation() - getThetaVariation()), getY() + i * Math.sin(getOrientation() - getThetaVariation()), getRadius()))
	 *			|				then	new.getPosition().getX() == getX() + i * Math.cos(getOrientation() - getThetaVariation())
	 *			|						new.getPosition().getY() == getY() + i * Math.cos(getOrientation() - getThetaVariation())
	 *			|			if(!getWorld().isImpassableAux(getX() + i * Math.cos(getOrientation() - getThetaVariation()), getY() + i * Math.sin(theta2), getRadius()))
	 *			|				then	getPosition().getX() == getX() + i * Math.cos(getOrientation() - getThetaVariation())
	 *			|						getPosition().getY() == getY() + i * Math.sin(theta2)
	 *			|						getPassablePositions().add(getPosition)
	 */

	private ArrayList<Position> getAllowedMovePositions() {
		ArrayList<Position> ps = new ArrayList<>();
		passablePositions.clear();
		for (double i = 0; i < getRadius(); i += getRadiusStepSize()) {
			for (double theta2 = getOrientation() - getThetaVariation(); theta2 <= getOrientation()
					+ getThetaVariation(); theta2 += getOrientationStepSize()) {
				double x = getXCoordinate() + i * Math.cos(theta2);
				double y = getYCoordinate() + i * Math.sin(theta2);
				if (getWorld().isAdjacentAux(x, y, getRadius())) {
					Position p = new Position();
					p.setXCoordinate(x);
					p.setYCoordinate(y);
					ps.add(p);
				}
				if (!getWorld().isImpassableAux(x, y, getRadius())) {
					Position passable = new Position();
					passable.setXCoordinate(x);
					passable.setYCoordinate(y);
					passablePositions.add(passable);
				}

			}
		}
		return ps;
	}

	/**
	 * Return the factor were the radius must be smaller as the distance to an impassable location.
	 * @return	The factor were the radius must be smaller as the distance to an impassable location.
	 * 			| resutl > 0
	 */
	private double getRadiusStepSize() {
		return 0.1;
	}

	/**
	 * Return the slope for the move of this worm.
	 *
	 * @param	distanceXCoordinate
	 * 			The distance in x-coordinates.
	 * @param 	distanceYCoorinate
	 * 			The distance in y-coordinates.
	 * @return	The slope of this worm.
	 * 			| Math.atan((this.getYCoordinta() - distanceYCoorinate) / this.getXCoordinate() - distanceXCoordinate)
	 */
	private double calculateSlope(double distanceXCoordinate,
			double distanceYCoorinate) {
		return Math.atan((getYCoordinate() - distanceYCoorinate)
				/ (getXCoordinate() - distanceXCoordinate));
	}

	/**
	 * Calculate the distance for moving this worm.
	 *
	 * @param	distanceXCoordinate
	 * 			The distance of the x-coordinate.
	 * @param 	distnaecYCoordinate
	 * 			The distance of the y-coordinate.
	 * @return	The calculated distance with the formula of Pythagoras.
	 * 			| result == Math.sqrt( Math.pow(getXCoordintae() - distanceXCoordinate, 2) +  Math.pow(getYCoordinate() - distanceYCoordinate, 2));
	 */
	private double calculateDistance(double distanceXCoordinate,
			double distnaecYCoordinate) {
		double a = Math.pow(getXCoordinate() - distanceXCoordinate, 2);
		double b = Math.pow(getYCoordinate() - distnaecYCoordinate, 2);
		double d = Math.sqrt(a + b);
		return d;
	}

	/**
	 * Return a position object with the best allowed move of this worm.
	 * @return	The position to move to.
	 * 			| for each allowed position a
	 * 			|	if(	calculateDistance(a.getXCoordinate(), a.getYCoordinate()))
	 * 			|		 then if(calculateDistance(a.getXCoordinate(), a.getYCoordinate()))
	 * 			|				then maxdistance == calculateDistance(a.getXCoordinate(), a.getYCoordinate())
	 * 			|					mindiversion == Math.abs(getOrientation() - calculateSlope(a.getXCoordinate(), a.getYCoordinate()))
	 * 			|	return bestmove
	 */
	private Position getBestMove() {
		Position p = new Position();
		double maxD = 0, minDiv = 500000000;
		for (Position a : getAllowedMovePositions()) {
			double d = calculateDistance(a.getXCoordinate(), a.getYCoordinate());
			double s = calculateSlope(a.getXCoordinate(), a.getYCoordinate());
			double div = Math.abs(getOrientation() - s);
			if (d <= getRadius() && d >= 0.1) {
				if (d > maxD && div < minDiv) {
					maxD = d;
					minDiv = div;
					p = a;
				}
			}
		}
		return p;
	}

	/**
	 * Return the best s move of this worm. This happens when there is no best allowed move.
	 *
	 * @return	Each new best position of this move.
	 * 			| for each passable position a
	 * 			|	if(calculateDistance(a.getXCoordinate(), a.getYCoordinate()) <= getRadius() && calculateDistance(a.getXCoordinate(), a.getYCoordinate()) >= getRadiusStepSize())
	 * 			|		then if(calculateDistance(a.getXCoordinate(), a.getYCoordinate()) > maxDiversion && calculateDistance(a.getXCoordinate(), a.getYCoordinate()) < minmumDiversion)
	 * 			|				then  maxDiversion == minimumDiversion
	 * 			|					  miniMumDiversion ==  Math.abs(getOrientation() - calculateSlope(a.getXCoordinate(), a.getYCoordinate()))
	 * 			|					  new.getPosition()	== getPassablePositions()
	 * 			|	resutl == bestPassabelMove()
	 */
	private Position getBestPassableMove() {
		Position p = new Position();
		double maxD = 0, minDiv = 500000000;
		for (Position a : passablePositions) {
			double d = calculateDistance(a.getXCoordinate(), a.getYCoordinate());
			double s = calculateSlope(a.getXCoordinate(), a.getYCoordinate());
			double div = Math.abs(getOrientation() - s);
			if (d <= getRadius() && d >= getRadiusStepSize()) {
				if (d > maxD && div < minDiv) {
					maxD = d;
					minDiv = div;
					p = a;
				}
			}
		}
		return p;
	}

	/**
	 * Return the cost for moving this worm.
	 *
	 * @param	slope
	 * 			The slope of this move.
	 * @return	The cost of moving this worm.
	 * 			| result == (int) Math.ceil(Math.abs(Math.cos(slope)) + Math.abs(4 * Math.sin(slope));
	 */
	private int calculateMovingCost(double slope) {
		int cost = 0;
		double a = Math.abs(Math.cos(slope));
		double b = Math.abs(4 * Math.sin(slope));
		cost = (int) Math.ceil(a + b);
		return cost;
	}

	/**
	 * Let this worm execute a move.
	 *
	 * @pre		The worm should be able to move.
	 * 			| canmove()
	 * @effect	If the best move is null then the worm is able to move.
	 * 			Else then
	 * 			|	If(getBestMove() == null)
	 *			|		then deduceActionPoints(calculateMovingCost(calculateSlope(getBestPassableMove().getXCoordinate(), getBestPassableMove().getYCoordinate())))
	 * @effect	The worm will fall
	 * 			| fall()
	 * @effect	There is a check if the worm can eat something.
	 * 			| checkIfWormCanEat()
	 */
	public void move() {
		if (!canMove()) {
			throw new ModelException("This worm cannot move!");
		}
		if (bestMove == null) {
			// Otherwise, if locations in the direction of ������ are passable
			// but
			// not adjacent to impassable terrain, the worm shall move there and
			// then drop passively to impassable terrain as explained below.
			// fall()
			Position p = getBestPassableMove();
			double s = calculateSlope(p.getXCoordinate(), p.getYCoordinate());
			int cost = calculateMovingCost(s);
			setPosition(p);
			checkIfWormCanEat();
			deduceActionPoints(cost);
			fall();
			checkIfWormCanEat();
		}

		double s = calculateSlope(bestMove.getXCoordinate(),
				bestMove.getYCoordinate());
		int cost = calculateMovingCost(s);

		setPosition(bestMove);
		checkIfWormCanEat();
		deduceActionPoints(cost);
	}

	/**
	 * Set the selected weapon of this worm to a random weapon.
	 * @effect	The selected weapon for each worm is set to a new random weapon
	 * 			| for each weapon
	 * 			|		if (this.getWeapon() != new.getWeapon)
	 * 			|			then this.getSelectedWeapon() == new.getSelectedWeapon
	 */
	public void selectNextWeapon() {
		Weapon originalWeapon = selectedWeapon;
		for (Weapon w : getWeapons()) {
			if (w != originalWeapon) {
				setSelectedWeapon(w);
			}
		}
	}

	/**
	 * Ables this worm to fire a weapon.
	 *
	 * @param	yield
	 * 			The propulsion yield of the selected weapon.
	 * @throws	ModelExeption
	 * 			This worm must be able to shoot.
	 * 			| canShoot()
	 * @effect	The worm's projectile is set.
	 * 			| Projectile(this, getWorld(), getXCoordinta(), getYCoordinate(),
	 *			| 		getOrientation(), yield, getSelectedWeapon)
	 * @effect	The activeness for this worm's projectile is set true.
	 * 			|	getActive() == true
	 * @effect	The action points of this worm are deduced.
	 * 			|deduceActionPoints(selectedWeapon.getShootingCost())
	 */
	public void shoot(int yield) {
		if (!canShoot()) {
			throw new ModelException("Can't shoot!");
		}
		Projectile p = new Projectile(this, getWorld(), getXCoordinate(),
				getYCoordinate(), getOrientation(), yield, selectedWeapon);
		getWorld().getProjectiles().add(p);
		setActiveProjectile(p);
		p.setActive(true);
		deduceActionPoints(selectedWeapon.getShootingCost());
	}

	/**
	 * Check if this worm is able to shoot.
	 * @return	True if and only if this worm has action points and is is not on impassable terrain.
	 * 			| hasEnoughActionPoints(getSelectedWeapon().getShootingCost()) && !getWorld()
	 *			| .isImpassable(getXCoordinate(), getYCoordinate(), getRadius()))
	 */
	public boolean canShoot() {
		return (hasEnoughActionPoints(selectedWeapon.getShootingCost()) && !getWorld()
				.isImpassable(getXCoordinate(), getYCoordinate(), getRadius()));
	}

	/**
	 * Check if this worm is able to fall down.
	 *
	 * @return	True if and only is the world is not adjacent.
	 * 			| !getWorld().isAdjacent(getXCoordinate(), getYCoordinate(), getRadius())
	 */
	public boolean canFall() {
		return !getWorld().isAdjacent(getXCoordinate(), getYCoordinate(),
				getRadius());
	}

	/**
	 * Check if this worm is able to move.
	 * @return	True if and only if this worm has enough action points to move, and the worm's location isn't adjacent.
	 * 			| if(getBestMove().getXCoordinate() == 0 || getBestMove().getYCoordinate() == 0)
	 * 			|		then result == hasEnoughActionPoints(calculateMovingCost(calculateSlope(moveAndFall.getXCoordinate(), moveAndFall.getYCoordinate())))
	 * @return	True if and only if this worm has enough action points to move, and the worm's location is adjacent.
	 * 			| result == hasEnoughActionPoints(calculateMovingCost(calculateSlope(getBestMove().getXCoordinate(),
				| 				getBestMove().getYCoordinate())))
	 */
	public boolean canMove() {
		bestMove = getBestMove();

		if (bestMove.getXCoordinate() == 0 || bestMove.getYCoordinate() == 0) {
			// there are no adjacent moves
			Position moveAndFall = (Position) passablePositions.toArray()[0];
			double slope = calculateSlope(moveAndFall.getXCoordinate(),
					moveAndFall.getYCoordinate());
			int cost = calculateMovingCost(slope);
			return hasEnoughActionPoints(cost);
		}

		double slope = calculateSlope(bestMove.getXCoordinate(),
				bestMove.getYCoordinate());
		int c = calculateMovingCost(slope);
		return hasEnoughActionPoints(c);
	}

	/**
	 * Return the team of this worm.
	 *
	 * @result	If the team of this worm is initialized, the team of this worm is returned.
	 * 			| result == this.getTeam()
	 * @result	If the team of this worm isn't initialize, a null reference is returned.
	 * 			| result == null
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Set this worm to a given team.
	 *
	 * @param	team
	 * 			The new team of this worm.
	 * @post	The worm is set to the given team
	 * 			| new.getTeam = team
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * @param	food
	 * 			Food that potential can be eaten by this worm.
	 * @return	True if and only if the body of the worm overlaps the body of the food.
	 * 			| result == if( Math.sqrt(Math.pow(getXCoordinate() - food.getXCoordinate(), 2)
	 *			| 			+ Math.pow(getYCoordinate() - food.getYCoordinate(), 2)) < food.getRadius() + 0.5)
	 */
	@Model
	private boolean overlaps(Food food) {
		double distance = Math.sqrt(Math.pow(
				getXCoordinate() - food.getXCoordinate(), 2)
				+ Math.pow(getYCoordinate() - food.getYCoordinate(), 2));
		if (distance < food.getRadius() + 0.5) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * The effect if this worm eats a food object.
	 *
	 * @param	food
	 * 			The food that gets eaten by this worm.
	 * @effect	The radius of this worm grows by a certain percentage.
	 * 			| new.getRadius() == this.getRadius * a certain percentage.
	 * @effect	The food isn't active anymore.
	 * 			| food.getActive() == false
	 * @effect	The food is removed from the world.
	 * 			| getWorld().getFoods().remove(food)
	 */
	@Model
	private void eat(Food food) {
		setRadius(getRadius() * 1.1);
		food.setActive(false);
		getWorld().getFoods().remove(food);
	}

	/**
	 * Check if this worm gets hit by a projectile
	 * @param	projectile
	 * 			A projectile this worm potentialy gets hit by.
	 * @return	True if and only if the body of the worm overlaps the body of the projectile.
	 * 			| result == if(Math.sqrt(Math.pow(getXCoordinate() - projectile.getXCoordinate(), 2)
	 *			|				+ Math.pow(getYCoordinate() - getProjectile().getYCoordinate(), 2)) <  projectile.getRadius() + 0.7)
	 */
	public boolean getsHitBy(Projectile projectile) {
		double a = Math.pow(getXCoordinate() - projectile.getXCoordinate(), 2);
		double b = Math.pow(getYCoordinate() - projectile.getYCoordinate(), 2);

		double distance = Math.sqrt(a + b);
		if (distance < projectile.getRadius() + 0.7) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets the current hit points to a lower value.
	 * @param	hitPointsToDeduce
	 * 			The value by witch the hit points shall decreases.
	 * @post	The new hit points will be set to the current hit point minus the hitPointsToDeduce
	 * 			| new.getHitPoints() == this.getHitPoints() - hitPointsToDeduce
	 */
	public void deduceHitPoints(int hitPointsToDeduce) {
		setHitPoints(getHitPoints() - hitPointsToDeduce);
	}

	/**
	 * Return a list collections referencing to all weapons of this worm.
	 *
	 * @return	The list of collection referencing to all weapons of this worm.
	 * 			| result == this.getWeapons()
	 */
	private ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	/**
	 * Set the collection of all weapons of this worm.
	 *
	 * @param	weapons
	 * 			The collection of all weapons of this worm.
	 * @return	The collection of all weapons of this worm.
	 * 			| new.getWeapons() == weapons.
	 */
	protected void setWeapons(ArrayList<Weapon> weapons) {
		this.weapons = weapons;
	}

	/**
	 * Set the selected weapon of this weapon to the new selected weapon.
	 *
	 * @param	selectedWeapon
	 * 			The weapon this worm has selected.
	 * 			| new.getSelectedWeapon() == selectedWeapon
	 */
	private void setSelectedWeapon(Weapon selectedWeapon) {
		this.selectedWeapon = selectedWeapon;
	}

	/**
	 * List collection references to the weapons displayed of this worm.
	 *
	 * @invar	The list isn't a null reference.
	 * 			| weapons != null
	 */
	private ArrayList<Weapon> weapons = new ArrayList<>();

	/**
	 * Check is the worm is able to eat a food.
	 *
	 * @effect	For each worm we check that the worm can eat it.
	 * 			| for each food object
	 * 			|	if(overlaps(food)
	 * 			|		then eat(food)
	 */
	public void checkIfWormCanEat() {
		ArrayList<Food> fs = new ArrayList<>();
		for (Food f : getWorld().getFoods()) {
			fs.add(f);
		}
		for (Food f : fs) {
			if (overlaps(f)) {
				eat(f);
			}
		}
	}

	/**
	 * Sets the current action points to a lower value.
	 * @param	actionPointsToDeduce
	 * 			The value by witch the action points shall decreases.
	 * @post	The new action points will be set to the current action point minus the actionPointsToDeduce
	 * 			| new.getActionPoints() == this.getActionPoints() - actionPointsToDeduce
	 */
	@Model
	private void deduceActionPoints(int actionPointsToDeduce) {
		setActionPoints(getActionPoints() - actionPointsToDeduce);
	}

	/**
	 * Return the activeness of the projectile when the worm is shooting.
	 */
	@Basic
	@Raw
	public Projectile getActiveProjectile() {
		return activeProjectile;
	}

	/**
	 * Set the activeness of the projectile when the worm is shooting.
	 *
	 * @param	activeProjectile
	 * 			The active projectile of this worm
	 * @post	The activeness of the projectile when the worm is shooting is equal to the given activeness when the worm is shooting.
	 * 			| new.getActiveProjectile() == activeProjectile
	 */
	@Raw
	public void setActiveProjectile(Projectile activeProjectile) {
		this.activeProjectile = activeProjectile;
	}

	/**
	 * Check if this object has a program.
	 *
	 * @return	Return true if and only if this worm has a program
	 * 			| result == getProgramm()
	 */
	public boolean hasProgram() {
		if (getProgram() == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Return the program of this worm.
	 */
	@Basic
	@Raw
	public Program getProgram() {
		return program;
	}

	/**
	 * Sets the new program of this worm to the given program.
	 * @param 	program
	 * 			The new program of this worm.
	 * 			The new program of this worm is set to the given program
	 * @post	| new.getProgram() == program
	 */
	@Raw
	public void setProgram(Program program) {
		this.program = program;
	}

}
