package worms.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class involving a two-dimensional world in which the game takes place.
 * There are slopes and obstacles in this world.
 * Each world has a certain immutable size expressed in meters.
 * 
 * @invar	Each object can only be placed once in a world.
 * 			| canHaveAsSameObjectTeam()
 * 			| canHaveAsSameObjectFood()
 * 			| canHaveAsSameObjectWorm()
 * @invar  	The width and height shoud be valid for this world.
 * 			| canHaveAsSize(getWith() || getHeight())
 * @invar	The number of teams in this world should be less or equal than the maximum number of teams.
 * 			| canAddTeam()
 * @author	Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * @version	3.0
 *
 * https://github.com/hazzarux/worms.git
 */
public class World {

	/******************************************************************************************************
	 * INSTANCE VARIABLES
	 *****************************************************************************************************/
	/**
	 *	Variable registering the width of the world.
	 */
	private double width;
	/**
	 *	Variable registering the height of the world.
	 */
	private double height;
	/**
	 * 	Two-dimensional array that registers if the given array of the world is accessible.
	 */
	private boolean[][] passableMap;
	/**
	 *	Object for generating random numbers.
	 */
	private Random random;
	/**
	 * List collection references to the food displayed in this world.
	 * @invar	| worms != null
	 */
	private Collection<Worm> worms = new ArrayList<>();

	/**
	 * List collection references to the food displayed in this world.
	 * @invar	| foods != null
	 */
	private Collection<Food> foods = new ArrayList<>();
	/**
	 * List collection references to the projectiles displayed in this world.
	 * @invar	| projectiles != null
	 */
	private Collection<Projectile> projectiles = new ArrayList<>();
	/**
	 * List collection references to the teams displayed in this world.
	 * @invar	| teams != null
	 */
	private Collection<Team> teams = new ArrayList<>();
	/**
	 * Variable registering if the game has started.
	 */
	private boolean gameStarted;
	/**
	 * Variable referencing to The curentWorm witch we are playing with.
	 * @invar	| teams != null
	 */
	private Worm currentWorm = null;
	/**
	 * Variable registering the number of worms this world has.
	 */
	private int wormCounter = 0;

	/******************************************************************************************************
	 * CONSTANTS
	 *****************************************************************************************************/

	/**
	 * Return the upper bound of this world.
	 */
	@Basic
	@Immutable
	@Model
	private static double getUpperBound() {
		return Double.MAX_VALUE;
	}

	/**
	 * Return the maximum number of teams.
	 */
	@Basic
	@Immutable
	@Model
	private static int getMaxNumberOfTeams() {
		return 10;
	}

	/**
	 * Return the factor were the radius must be smaller as the distance to an impassable location.
	 */
	@Model
	@Basic
	@Immutable
	private static double getRadiusStepSize() {
		return 0.1;
	}

	/**
	 * Return the precision with the divergences may be sampled.
	 */
	@Basic
	@Immutable
	@Model
	private static double getAngleStep() {
		return 0.0175;
	}

	/******************************************************************************************************
	 * CONSTRUCTOR
	 *****************************************************************************************************/
	/**
	 * Initialize this new world with a given width, height, passableMap and a random generator.
	 *
	 * @param	width
	 * 			The width of this world.
	 * @param	height
	 * 			The height of this world.
	 * @param	passableMap
	 * 			A two two dimensional array that indicates with part of the map is passable and impassable.
	 * @param	random
	 * 			A random Generator that can create random aspect for this world.
	 * @post	| new.getWidth() = width
	 * @post	| new.getHeight() = height
	 * @post	| new.getPassableMap() == passableMap
	 * @post	| new.getRandom() = random
	 */
	@Raw
	public World(double width, double height, boolean[][] passableMap,
			Random random) {
		setWidth(width);
		setHeight(height);
		setPassableMap(passableMap);
		setRandom(random);
	}

	/******************************************************************************************************
	 * CONSTANTS
	 *****************************************************************************************************/
	/**
	 * Return the minimum number of worms that is displayed in this world.
	 */
	@Model
	@Basic
	@Immutable
	private int getMinimumCounterWorm() {
		return 0;
	}

	/******************************************************************************************************
	 * GETTERS & SETTERS
	 *****************************************************************************************************/

	/**
	 * Return the collection of all worms that are in the world.
	 */
	@Basic
	public Collection<Worm> getWorms() {
		return worms;
	}

	/**
	 * Sets the collection of all worms that are in the world.
	 *
	 * @param	worms
	 * 			A new collection of all worms in the world.
	 * @post	| new.getWorms() == worms
	 */
	@Raw
	private void setWorms(Collection<Worm> worms) {
		this.worms = worms;
	}

	/**
	 * Return the number of worms that is displayed in this world.
	 */
	@Basic
	private int getCounterWorm() {
		return wormCounter;
	}

	/**
	 * Return the collection of all food that are in the world.
	 */
	@Basic
	@Raw
	public Collection<Food> getFoods() {
		return foods;
	}

	/**
	 * Sets the collection of all food that are in the world.
	 *
	 * @param	worms
	 * 			A new collection of all food in the world.
	 * @post	| new.getFoods() == foods
	 */
	@Raw
	private void setFoods(Collection<Food> foods) {
		this.foods = foods;
	}

	/**
	 * Return the collection of all projectiles that are in this world.
	 */
	@Basic
	@Raw
	public Collection<Projectile> getProjectiles() {
		return projectiles;
	}

	/**
	 * Sets the collection of all projectiles that are in this world.
	 *
	 * @param	projectiles
	 * 			A new collection of projectiles in the world.
	 * @post	| new.getProjectiles() == projectiles
	 */
	@Raw
	private void setProjectiles(Collection<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	/**
	 * Return the collection of teams in this world.
	 */
	@Basic
	@Raw
	private Collection<Team> getTeams() {
		return teams;
	}

	/**
	 * Sets the collection of teams in this world
	 * @param	teams
	 * 			The new collection of teams in this world
	 * @post	| new.getTeams() == teams
	 */
	@Raw
	private void setTeams(Collection<Team> teams) {
		this.teams = teams;
	}

	/**
	 * Return the width of this world.
	 */
	@Basic
	@Raw
	@Immutable
	private double getWidth() {
		return width;
	}

	/**
	 * Set the width of this world to the given width.
	 * @param	width
	 * 			The new width of this world.
	 * @pre		| canHaveAsSze(width)
	 * @post	| new.getWidth() == width
	 */
	@Raw
	private void setWidth(double width) {
		if (!canHaveAsSize(width)) {
			throw new ModelException("Width should be valid.");
		}
		this.width = width;
	}

	/**
	 *	Return the height of this world.
	 */
	@Basic
	@Raw
	@Immutable
	private double getHeight() {
		return height;
	}

	/**
	 * Set the height of this world to the given height.
	 * @param	height
	 * 			The new height of this world.
	 * @pre		| canHaveAsSize(height)
	 * @post	| new.getHeight() == height
	 */
	@Raw
	private void setHeight(double height) {
		if (!canHaveAsSize(height)) {
			throw new ModelException("Height should be valid.");
		}
		this.height = height;
	}

	/**
	 * Return a two-dimensional map with all passablemap positions.
	 */
	@Basic
	@Raw
	private boolean[][] getPassableMap() {
		return passableMap;
	}

	/**
	 * Set the two-dimensional map with all passable map positions to the new passableMap .
	 * @param	passableMap
	 * 			The new two-dimensional map with all passable map positions.
	 * @post	| new.getPassableMap() == passableMap
	 */
	@Raw
	private void setPassableMap(boolean[][] passableMap) {
		this.passableMap = passableMap.clone();
	}

	/**
	 * Return a random generator for this world.
	 */
	@Basic
	@Raw
	private Random getRandom() {
		return random;
	}

	/**
	 * Set the new random generator to the given random generator.
	 * @param 	random
	 * 			The random generator for this world.
	 * @post	| new.getRandom() == random
	 */
	@Raw
	private void setRandom(Random random) {
		this.random = random;
	}

	/**
	 * Return the current worm that is executing it's turn.
	 */
	@Basic
	@Raw
	public Worm getCurrentWorm() {
		return currentWorm;
	}

	/**
	 * Set the new current worm to a given current worm.
	 *
	 * @param	currentWorm
	 * 			The new currentWorm that is executing it's turn.
	 * @post	| new.getCurrentWorm() == currentWorm
	 */
	@Raw
	private void setCurrentWorm(Worm currentWorm) {
		this.currentWorm = currentWorm;
	}

	/**
	 * Set the started game to the new started game.
	 * @param	gameStarted
	 * 			Value that decides if the game is started.
	 * @post	| new.getStarted() == gameStarted
	 */
	private void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	/**
	 * Return the active projectile in this world.
	 * @return	|  getProjectiles().toArray()[0]
	 */
	public Projectile getActiveProjectile() {
		return (Projectile) getProjectiles().toArray()[0];
	}

	/******************************************************************************************************
	 * VALIDATORS
	 *****************************************************************************************************/
	/**
	 * Check if the world has a valid width or height.
	 *
	 * @param 	widthOrHeight
	 * 		  	The width/height to check.
	 * @return	| result == widthOrHeight >= 0 && widhtOrHeight <= getUpperBound()
	 */
	private boolean canHaveAsSize(double widthOrHeight) {
		return (widthOrHeight >= 0 && widthOrHeight <= getUpperBound());
	}

	/**
	 * Check if there is still is place for adding a new team.
	 * @return	| result == this.getTeams().size() < getMaxNumberOfTeams()
	 */
	@Model
	private boolean canHavaAsExtraTeam() {
		return getTeams().size() < getMaxNumberOfTeams();
	}

	/**
	 * Check if the object is adjacent.
	 *
	 * @param	xCoordinate
	 * 			The x-coordinate of this object.
	 * @param	yCoordinate
	 * 			The y-coordinate of this object.
	 * @param	radius
	 * 			The radius of this object.
	 *
	 * @return	| if (xCoordinate < 0 || yCoordinate < 0)
	 *			| 		then result == false
	 *			|	 else if (xCoordinate > getWidth() || yCoordinate > getHeight())
	 *			|		then result == false
	 * 			| else
	 * 			|	result == (!isImpassable(xCoordinate, yCoordinate, radius) && this.isImpassable(xCoordinate, yCoordinate, radius
	 *			|  					+ radius * getRadiusStepSize()))
	 */
	public boolean isAdjacent(double xCoordinate, double yCoordinate,
			double radius) {
		if (xCoordinate < 0 || yCoordinate < 0) {
			return false;
		} else if (xCoordinate > getWidth() || yCoordinate > getHeight()) {
			return false;
		}
		return (!isImpassable(xCoordinate, yCoordinate, radius) && isImpassable(
				xCoordinate, yCoordinate, radius + radius * getRadiusStepSize()));
	}

	/**
	 * Check if the game has started.
	 *
	 * @return	| result == this.isGameStarted()
	 */
	private boolean isGameStarted() {
		return gameStarted;
	}

	/**
	 * Check if the object is still in the world.
	 *
	 * @param 	xCoordinate
	 * 			The x-coordinate of this object.
	 * @param 	yCoordinate
	 * 			The y-coordinate of this object.
	 * @return	| if(xCoordinate < 0 || yCoordinate < 0)
	 * 			|		result == false
	 * 			| else if( xCoordinate > getWidth() || yCoordinate > getHeight())
	 * 			|		then result == false
	 * 			| else
	 * 			|   result == (xCoordinate <= this.getWidth() && yCoordinate <= this.getHeight())
	 */
	public boolean isInside(double xCoordinate, double yCoordinate) {
		if (xCoordinate < 0 || yCoordinate < 0) {
			return false;
		} else if (xCoordinate > getWidth() || yCoordinate > getHeight()) {
			return false;
		}
		return (xCoordinate <= getWidth() && yCoordinate <= getHeight());
	}

	/**
	 * Check if the given position expressed in coordinates is located at an adjacent map positoin.
	 * @param	xCoordinate
	 * 			The xCoordinate of this object.
	 * @param	yCoordinate
	 * 			The yCoordinate of this object.
	 * @return	|  if (xCoordinate < 0 || yCoordinate < 0)
	 *			|		then result ==  false
	 *	 		|  else if (xCoordinate > getWidth() || yCoordinate > getHeight())
	 *			|		then resutl == false
	 *			|	else
	 * 			|		result == isAdjacent(xCoordinate, yCoordinate, new Food(this, xCoordinate, yCoordinate).getRadius());
	 */
	@Model
	private boolean canHaveAsLocation(double xCoordinate, double yCoordinate) {
		if (xCoordinate < 0 || yCoordinate < 0) {
			return false;
		} else if (xCoordinate > getWidth() || yCoordinate > getHeight()) {
			return false;
		}
		return isAdjacent(xCoordinate, yCoordinate, new Food(this, xCoordinate,
				yCoordinate).getRadius());
	}

	/******************************************************************************************************
	 * FUNCTIONS
	 *****************************************************************************************************/
	/**
	 * Add a new team to the current amount of teams
	 *
	 * @param	newName
	 * 			The name of this new team.
	 * @throws	ModelException
	 * 			| !canAddTeam()
	 * @post	| getTeam().add(Team(newName))
	 */
	public void addEmptyTeam(String newName) {
		// defensive
		if (!canHavaAsExtraTeam()) {
			throw new ModelException(
					"Can't add a team because there are enough teams already");
		}
		Team t = new Team(newName);
		if (canHaveAsSameObjectTeam(t)) {
			getTeams().add(t);
		}
	}

	/**
	 * Check if the given object is already in this world.
	 * @param	team
	 * 			The team that potentially is placed in this world.
	 * @return	|	if(team == null)
	 * 			|		then result == false
	 *			|	for each team team
	 *			|		if(team == food)
	 *			|			then result == false
	 *			|		else
	 *			|			result == true
	 */
	private boolean canHaveAsSameObjectTeam(Team team) {
		if (team == null) {
			return false;
		}
		for (Team t : getTeams()) {
			if (t == team) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Add a new food to the current amount of teams.
	 * @throws	ModelException
	 * 			| isGameStarted()
	 * @effect	| getFoods().add(reateFood(xCoordinate, yCoordinate))
	 */
	public void addNewFood() {
		if (isGameStarted()) {
			throw new ModelException(
					"Cannot add new food because the game is already started!");
		}
		double x = 0, y = 0;
		x = getRandom().nextDouble() * getWidth();
		y = getRandom().nextDouble() * getHeight();
		while (!canHaveAsLocation(x, y)) {
			x = getRandom().nextDouble() * getWidth();
			y = getRandom().nextDouble() * getHeight();
		}
		Food f = createFood(x, y);
		if (canHaveAsSameObjectFood(f)) {
			getFoods().add(f);
		}
	}

	/**
	 * Check if the given object is already in this world.
	 * @param	food
	 * 			The food that potentially is placed in this world.
	 * @return	|	if(food == null)
	 * 			|		then result == false
	 *			|	for each food voedsel
	 *			|		if(voedsel == food)
	 *			|			then result == false
	 *			|		else
	 *			|			result == true
	 */
	private boolean canHaveAsSameObjectFood(Food food) {
		if (food == null) {
			return false;
		}
		for (Food f : getFoods()) {
			if (f == food) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Create a new food object in this world.
	 * @param	xCoordinate
	 * 			The x-coordinate of this new food.
	 * @param	yCoordinate
	 * 			The y-coordinate of this new food.
	 * @return	| new.getFood(xCoordinate, yCoordinate)
	 */
	public Food createFood(double xCoordinate, double yCoordinate) {
		Food f = new Food(this, xCoordinate, yCoordinate);
		return f;
	}

	/**
	 * Create a new worm object in this world.
	 *
	 * @param	xCoordinate
	 * 			The x-coordinate of this new worm.
	 * @param	yCoordinate
	 * 			The y-coordinate of this new worm.
	 * @param	direction
	 * 			The direction of this new worm.
	 * @param	radius
	 * 			The radius of this new worm.
	 * @param	name
	 * 			The name of this new Worm.
	 * @return	Worm(this, xCoordinate, yCoordinate, direction, radius, name)
	 */
	public Worm createWorm(double xCoordinate, double yCoordinate,
			double direction, double radius, String name) {
		Worm w = new Worm(this, xCoordinate, yCoordinate, direction, radius,
				name);
		return w;
	}

	/**
	 * Return the winning worm or team of this team.
	 *
	 * @return	| if (getWorms().size() == 1)
	 * 			| 	then result == (Worm) getWorms().toArray()[0].getName()
	 * @return	| if(getWorms.size() !=0)
	 * 			|	then result == (Worm) getWorms().toArray()[0].getTeamName()
	 */
	public String getWinner() {
		Worm winningWorm = (Worm) getWorms().toArray()[0];
		if (getWorms().size() == 1) {
			return winningWorm.getName();
		} else {
			return winningWorm.getTeamName();
		}
	}

	/**
	 * Check if the game is finished.
	 * @return |
	 * 		   | if(( (Worm) getWorms().toArray()[0]).getTeam() == null) ||
	 * 		   | for(w : getWorms())
	 * 		   | 	then if(( (Worm) getWorms().toArray()[0]).getTeam() == null)
	 * 		   | 		result == false
	 * @return | if( getWorms().size() == 1)
	 * 		   |	then result == false
	 */
	public boolean isGameFinished() {
		if (getWorms().size() == 1) {
			return true;
		}

		Worm firstW = (Worm) getWorms().toArray()[0];
		Team t = firstW.getTeam();
		if (t == null) {
			return false;
		}
		for (Worm w : getWorms()) {
			Team t2 = w.getTeam();
			if (t2 != t) {
				return false;
			}
		}
		return true;
		// The game is finished once only one worm or worms that belong to the
		// same team are left in the game world and all other worms have been
		// removed from that game world.
	}

	/**
	 * @param	xCoordinate
	 * 			x-coordinate of this new object placed in this world.
	 * @param	yCoordinate
	 * 			y-coordinate of this new object placed in this world.
	 * @param	radius
	 * 			The radius of this new object placed in this world.
	 * @return	| if(xCoordinate < 0 || yCoordinate < 0 || radius < 0)
	 * 			|		then result == false
	 * 			| else if(xCoordinate > getWidth() || yCoordinate > getHeight()|| radius > getWidth() || radius > getHeight())
	 * 			|		then result == false
	 * 			| else  if (getPassableMap()[getPassableMap().length -  Math.round(yCoordinate + radius * Math.sin(i)
	 *			|  	(getPassableMap().length / getHeight())][Math.round(xCoordinate + radius * Math.cos(i)
	 *			|		 (getPassableMap()[0].length / getWidth())] == false)
	 *			| 		then result == true
	 */
	public boolean isImpassableAux(double xCoordinate, double yCoordinate,
			double radius) {
		// Total Manner
		if (xCoordinate < 0 || yCoordinate < 0 || radius < 0) {
			return false;
		} else if (xCoordinate > getWidth() || yCoordinate > getHeight()
				|| radius > getWidth() || radius > getHeight()) {
			return false;
		}
		for (double i = 0; i < 2 * Math.PI; i += getAngleStep()) {
			// i is the angle to check
			double newX = xCoordinate + radius * Math.cos(i);
			double newY = yCoordinate + radius * Math.sin(i);
			int xToCheck = (int) Math
					.floor(((newX * (getPassableMap()[0].length / getWidth()))));
			int yToCheck = (int) Math.ceil(newY
					* (getPassableMap().length / getHeight()));
			try {
				if (getPassableMap()[getPassableMap().length - yToCheck][xToCheck] == false) {
					return true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}
		return false;
	}

	/**
	 * Check if the given world is passable.
	 * @param	xCoordinate
	 * 			The x-coordinate of the new object that is placed in this world.
	 * @param 	yCoordinate
	 * 			The y-coordinate of the new object that is placed in this world.
	 * @param 	radius
	 * 			The radius of this object.
	 * @return	| if (xCoordinate < 0 || yCoordinate < 0 || radius < 0) {
	 *			|		result == false
	 *	 		| else if (xCoordinate > getWidth() || yCoordinate > getHeight()
	 *			|		|| radius > getWidth() || radius > getHeight())
	 *			|		result == false
	 * 			|for each radius larger than the total radius of this object
	 * 			|	then	if(isImpassableAux(xCoordinate, yCoordinate, radius))
	 *			|				then result == true
	 */
	public boolean isImpassable(double xCoordinate, double yCoordinate,
			double radius) {
		// Total Manner
		if (xCoordinate < 0 || yCoordinate < 0 || radius < 0) {
			return false;
		} else if (xCoordinate > getWidth() || yCoordinate > getHeight()
				|| radius > getWidth() || radius > getHeight()) {
			return false;
		}
		for (double i = 0; i < radius; i += getRadiusStepSize()) {
			if (isImpassableAux(xCoordinate, yCoordinate, radius)) {
				return true;
			}
		}
		return false;
	}

	public void startGame() {
		if (isGameStarted() == true) {
			throw new ModelException("Game is already started!");
		}
		setGameStarted(true);
		Worm w = (Worm) getWorms().toArray()[wormCounter];
		setCurrentWorm(w);
		wormCounter++;
		wormPowerUp();
		if (getCurrentWorm().hasProgram()) {
			getCurrentWorm().getProgram().execute(getCurrentWorm());
		}
	}

	/**
	 * Check if the turn of this worms has ended.
	 * @param	worm
	 * 			The worm to check.
	 * @return	| result == (!worm.hasHitPoints() || !worm.hasActionPoints())
	 */
	@Model
	private boolean turnHasEnded(Worm worm) {
		return (!worm.hasHitPoints() || !worm.hasActionPoints());
	}

	/**
	 * Set the next turn of a worm.
	 *
	 *@effect	| setCurrentWorm(getWorms().toArray()[getCounterWorm()])
	 *@effect	| startNextTurn();
	 *@effect	| wormPowerUp()
	 *@post		| new.getCounterWorm() == this.getCounterWorm() + 1
	 */
	public void startNextTurn() {
		if (turnHasEnded(getCurrentWorm())) {
			if (wormCounter == getWorms().size()) {
				wormCounter = 0;
			}
			Worm w = (Worm) getWorms().toArray()[wormCounter];
			if (shouldBeRemovedFromThisWorld(w)) {
				getWorms().remove(w);
				wormCounter++;
				startNextTurn();
			}
			setCurrentWorm(w);
			wormPowerUp();
			if (w.hasProgram()) {
				w.getProgram().execute(getCurrentWorm());
			}
			wormCounter++;
		}
	}

	/**
	 * Check if this worm is still allowed to be in this world.
	 * @param 	worm
	 * 			The worm that shall be removed
	 * @return	| result == !(isInside(worm.getX(), worm.getY()) || !worm.hasHitPoints())
	 */
	private boolean shouldBeRemovedFromThisWorld(Worm worm) {
		return (!isInside(worm.getXCoordinate(), worm.getYCoordinate()) || !worm
				.hasHitPoints());
	}

	/**
	 * Changes the state of the worm after eating.
	 *
	 * @effect	|	getCurrentWorm().checkIfWormCanEat();
	 * @effect	|	getCurrentWorm().increaseHitPointsByTen();
	 * @effect	|	getCurrentWorm().rejuiceActionPoints();
	 */
	@Model
	private void wormPowerUp() {
		getCurrentWorm().checkIfWormCanEat();
		getCurrentWorm().increaseHitPointsBy();
		getCurrentWorm().rejuiceActionPoints();
	}

	/**
	 * Check if the worm is adjacent.
	 * @param	xCoordinate
	 * 			The x-coordinate of this worm.
	 * @param 	yCoordinate
	 * 			The y-coordinate of this worm.
	 * @param 	radius
	 * 			The radius of this worm.
	 * @return	| if(xCoordinate < 0 || yCoordinate < 0 || radius < 0)
	 *			|		then result == false
	 *			| else if(xCoordinate > getWidht() || yCoordinate > getHeight()|| radius > getWidth() || radius> getHeight())
	 *			|		then result == false
	 *			| else
	 *			|		result == (!isImpassableAux(xCoordinate, yCoordinate, radius) && isImpassableAux(
				| 					xCoordinate, yCoordinate, radius + radius * getRadiusStepSize()))
	 */
	public boolean isAdjacentAux(double xCoordinate, double yCoordinate,
			double radius) {
		// Total Manner
		if (xCoordinate < 0 || yCoordinate < 0 || radius < 0) {
			return false;
		} else if (xCoordinate > getWidth() || yCoordinate > getHeight()
				|| radius > getWidth() || radius > getHeight()) {
			return false;
		}
		return (!isImpassableAux(xCoordinate, yCoordinate, radius) && isImpassableAux(
				xCoordinate, yCoordinate, radius + radius * getRadiusStepSize()));
	}

	/**
	 * Creates a new worm in this world.
	 *
	 * @param	xCoordinate
	 * 			The x-coordinate of this new worm.
	 * @param	yCoordinate
	 * 			The y-coordinate of this new worm.
	 * @param	direction
	 * 			The direction of this new worm.
	 * @param	radius
	 * 			The radius of this new worm.
	 * @param	name
	 * 			The name of this new worm.
	 * @param	program
	 * 			The program of this new wrom.
	 * @return	| return == new Worm(this, xCoordinate, yCoordinate, direction, radius, name, program)
	 */
	public Worm createWorm(double xCoordinate, double yCoordinate,
			double direction, double radius, String name, Program program) {
		Worm w = new Worm(this, xCoordinate, yCoordinate, direction, radius,
				name, program);
		return w;
	}

	/**
	 * Add a new worm to the current worm.
	 * @param	program
	 * 			The program that is given with this worm.
	 * @throws	ModelException
	 * 			| isGameStarted()
	 * @post	| while(Worm(this, 1, 1, 2, 1.0, "Auxiliary worm").canHaveAsOrientation(getRandom().nextDouble()))
	 * 			|		then getWorm[getCurrentWorm].getOrientation() == getRandom().nextDouble()
	 * 			| while(Worm(this, 1, 1, 2, 1.0, "Auxiliary worm").canHaveAsRadius(getRandom().nextDouble()))
	 * 			|		then getWorm[getCurrentWorm].getRadius() == getRandom().nextDouble()
	 * 			|	while (!isAdjacent( getRandom().nextDouble() * width, getRandom().nextDouble() * height, radius))
	 *			|			then getWorm[getCurrentWorm()].getXcoordinate() = getRandom().nextDouble() * width
	 *			|				 getWorm[getCurrentWorm()].getYcoordinate() = getRandom().nextDouble() * height
	 *			|	getWorms().add(ew Worm(this, x, y, orientation, radius, "New worm", program))
	 *
	 */
	public void addNewWorm(Program program) {
		if (isGameStarted()) {
			throw new ModelException(
					"Cannot add new worm because the game is already started!");
		}
		double x, y, orientation, radius;
		x = getRandom().nextDouble() * getWidth();
		y = getRandom().nextDouble() * getHeight();
		orientation = getRandom().nextDouble();
		radius = getRandom().nextDouble();
		Worm auxW = new Worm(this, 1, 1, 2, 1.0, "Auxiliary worm");
		while (!auxW.canHaveAsOrientation(orientation)) {
			orientation = getRandom().nextDouble();
		}
		while (!auxW.canHaveAsRadius(radius)) {
			radius = getRandom().nextDouble();
		}
		while (!isAdjacent(x, y, radius)) {
			x = getRandom().nextDouble() * width;
			y = getRandom().nextDouble() * height;
		}
		Worm w = new Worm(this, x, y, orientation, radius, "New worm", program);
		if (canHaveAsSameObjectWorm(w)) {
			getWorms().add(w);
		}

	}

	/**
	 * Check if the given object is already in this world.
	 * @param	worm
	 * 			The worm that potentially is placed in this world.
	 * @return	|	if(worm == null)
	 * 			|		then result == false
	 *			|	for each worm wormen
	 *			|		if(wormen == food)
	 *			|			then result == false
	 *			|		else
	 *			|			result == true
	 */
	private boolean canHaveAsSameObjectWorm(Worm worm) {
		if (worm == null) {
			return false;
		}
		for (Worm f : getWorms()) {
			if (f == worm) {
				return false;
			}
		}
		return true;
	}

}
