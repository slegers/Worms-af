package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 *	A class that represents a food object that can be found in the game with a given radius and position.
 *
 *	@author		Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * 	@version	3.0
 *
 * https://github.com/hazzarux/worms.git
 */
public class Food {
	/******************************************************************************************************
	 * INSTANCE VARIABLES
	 *****************************************************************************************************/
	/**
	 * Variable referencing to the position of this food.
	 */
	private Position position = new Position();
	/**
	 *	Variable registering if the food is active.
	 */
	private boolean isActive;
	/**
	 * Variable referencing to the world of this food.
	 */
	private World world;

	/******************************************************************************************************
	 * CONSTRUCTOR
	 *****************************************************************************************************/

	/**
	 *  Initialize this new Food with given x-coordinate, y-coordinate and world.
	 *
	 * @param	world
	 * 			The world this food is placed in.
	 * @param	xCoordinate
	 * 			The x-coordinate for this new food.
	 * @param 	yCoordinate
	 * 			The y-coordinate for this new food.
	 * @post	| new.getWorld() == world
	 * @post	| new.getXCoordinate() == xCoordinate
	 * @post	| new.getYCoordinate() == yCoordinate
	 * @post	| new.getActive() == true
	 */
	@Raw
	public Food(World world, double xCoordinate, double yCoordinate) {
		setWorld(world);
		setXCoordinate(xCoordinate);
		setYCoordinate(yCoordinate);
		setActive(true);
	}

	/******************************************************************************************************
	 * CONSTANTS
	 *****************************************************************************************************/
	/**
	 * Return the radius of this food object.
	 */
	@Basic
	@Immutable
	public static double getRadius() {
		return 0.20;
	}

	/******************************************************************************************************
	 * GETTERS & SETTERS
	 *****************************************************************************************************/

	/**
	 * Return the x-coordinate of this food.
	 */
	@Basic
	@Raw
	public double getXCoordinate() {
		return getPosition().getXCoordinate();
	}

	/**
	 * Set the x-coordinate of this food to the given x-coordinate.
	 *
	 * @param	xCoordinate
	 * 			The x-coordinate of this food.
	 * @post	| new.getXCoordinate() == xCoordinate
	 */
	@Raw
	public void setXCoordinate(double xCoordinate) {
		getPosition().setXCoordinate(xCoordinate);
	}

	/**
	 * Return the y-coordinate of this food.
	 */
	@Basic
	@Raw
	public double getYCoordinate() {
		return getPosition().getYCoordinate();
	}

	/**
	 * Set the y-coordinate of this food to the given y-coordinate.	 *
	 * @param	yCoordinate
	 * 			The y-coordinate of this food.
	 * @post	| new.getYCoordinate() == yCoordinate
	 */
	@Raw
	public void setYCoordinate(double yCoordinate) {
		getPosition().setYCoordinate(yCoordinate);
	}

	/**
	 * Return the the position of this food.
	 */
	@Basic
	@Raw
	public Position getPosition() {
		return position;
	}

	/**
	 * Set the position of this food in meters.
	 *
	 * @param	position
	 * 			The position Object of this worm.
	 * @post	| new.getPosition() == position
	 */
	@Raw
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Return true if and only if the food is active.
	 */
	@Basic
	@Raw
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Set the activeness of this worm.
	 * @param 	activeness
	 *			The active state of this worm.
	 *@post		| new.getActive() == activeness
	 */
	@Raw
	public void setActive(boolean activeness) {
		isActive = activeness;
	}

	/**
	 * Return the world were this food is displayed.
	 */
	@Basic
	@Raw
	public World getWorld() {
		return world;
	}

	/**
	 * Set the world of this food to the given world.
	 * @param	world
	 * 			The world were this food is displayed.
	 * @post	| new.getWorld() == world
	 */
	@Raw
	public void setWorld(World world) {
		this.world = world;
	}

}
