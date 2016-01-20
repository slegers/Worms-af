package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of teams involving a number of worms.
 * A Team name and the maximum number of teams cannot change during the game.
 *
 * @invar	| canHaveAsName(getName)
 * @author	Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * @version	3.0
 *
 * https://github.com/hazzarux/worms.git
 */
public class Team {

	/******************************************************************************************************
	 * INSTANCE VARIABLES
	 *****************************************************************************************************/
	/**
	 *Variable registering the name of this team.
	 */
	private String name;

	/******************************************************************************************************
	 * CONSTRUCTOR
	 *****************************************************************************************************/
	/**
	 * Initialize this new Team with given name.
	 *
	 * @param 	name
	 * 			The name of this team.
	 * @post	| new.getName() == name
	 */
	@Raw
	public Team(String name) {
		setName(name);
	}

	/******************************************************************************************************
	 * GETTERS AND SETTERS
	 *****************************************************************************************************/

	/**
	 * Return the regular expression needed to validate the name of this worm.
	 * @return	| result == "[A-Z]([a-zA-Z]+)"
	 */
	private static String getRegex() {
		return "[A-Z]([a-zA-Z]+)";
	}

	/**
	 * Return the name of this team.
	 */
	@Basic
	@Raw
	public String getName() {
		return name;
	}

	/******************************************************************************************************
	 * VALIDATORS
	 *****************************************************************************************************/

	/**
	 * Check if the given name of this team is a correct name.
	 *
	 * @param	name
	 * 			The new name of this worm.
	 * @return 	| result == name.matches(getRegex())
	 */
	@Raw
	private boolean canHaveAsName(String name) {
		return name.matches(getRegex());
	}

	/**
	 * Set the name of this Team.
	 *
	 * @param	name
	 * 			The name of this team.
	 * @throws	ModelException
	 * 			| !canHaveAsName(name)
	 * @post 	| new.name = name
	 */
	@Raw
	public void setName(String name) {
		// defensive
		if (!canHaveAsName(name)) {
			throw new ModelException("The team's name is not valid.");
		}
		this.name = name;
	}

}
