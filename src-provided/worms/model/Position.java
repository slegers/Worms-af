package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class that represents the position of some objects of the game with a x-coordinate and y-coordinate.
 *
 * @author	Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * @version	3.0
 *
 * https://github.com/hazzarux/worms.git
 */
public class Position {
	/******************************************************************************************************
	 * INSTANCE VARIABLES
	 *****************************************************************************************************/
	/**
	 *Variable registering the the x-coordinate of this object.
	 */
	private double xCoordinate;
	/**
	 *Variable registering the the y-coordinate of this object.
	 */
	private double yCoordinate;

	/******************************************************************************************************
	 * CONSTRUCTOR
	 *****************************************************************************************************/

	/**
	 * Initialize  this new position with a given x-coordinate and a given y-coordinate.
	 *
	 * @effect		| this(0,0)
	 */
	public Position() {
		this(0.0, 0.0);
	}

	/**
	 * Initialize this new position with a given x-coordinate and a given y-coordinate.
	 * @param	xCoordinate
	 * 			The x-coordinate of this new object in meters.
	 * @param	yCoordinate
	 * 			The y-coordinate of this new object in meters.
	 * @post	| getXCoordinate() == xCoordinate
	 * @post	| getYCoordinate() == yCoordinate
	 */
	public Position(double xCoordinate, double yCoordinate) {
		setXCoordinate(xCoordinate);
		setYCoordinate(yCoordinate);
	}

	/******************************************************************************************************
	 * GETTERS AND SETTERS
	 *****************************************************************************************************/
	/**
	 * Return the x-coordinate of this object in meters.
	 */
	@Raw
	@Basic
	public double getXCoordinate() {
		return xCoordinate;
	}

	/**
	 * Set the x-coordinate of this worm to the given x-coordinate.
	 * The x-coordinate is expressed in meters.
	 *
	 * @param	xCoordinate
	 * 			The x-coordinate of this worm.
	 * @throws	ModelException
	 * 			| if(getXCoordinate() < 0)
	 * 			| Double.isNan(x)
	 * @post	| new.getXCoordinate() == xCoordinate
	 *
	 */
	@Raw
	public void setXCoordinate(double xCoordinate) {
		// defensive
		if (xCoordinate < 0) {
			throw new ModelException("X-coordinate should be a positive number");
		}
		if (Double.isNaN(xCoordinate)) {
			throw new ModelException("X-coordinate should be a number");
		}
		this.xCoordinate = xCoordinate;
	}

	/**
	 * Return the y-coordinate of this object in meters.
	 */
	@Basic
	@Raw
	public double getYCoordinate() {
		return yCoordinate;
	}

	/**
	 * Set the y-coordinate of this worm to the given y-coordinate.
	 * The y-coordinate is expressed in meters.
	 *
	 * @param	yCoordinate
	 * 			The y-coordinate of this worm.
	 * @throws	ModelExeption
	 * 			| if (getYCoordinate < 0)
	 * 			| Double.isNan(yCoordinate)
	 * @post	| this.getYCoordinate() == yCoordinate
	 *
	 */
	@Raw
	public void setYCoordinate(double yCoordinate) {
		// defensive
		if (yCoordinate < 0) {
			throw new ModelException("Y-coordinate should be a positive number");
		}
		if (Double.isNaN(yCoordinate)) {
			throw new ModelException("Y-coordinate should be a number");
		}
		this.yCoordinate = yCoordinate;
	}
}
