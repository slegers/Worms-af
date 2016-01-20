package worms.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import worms.gui.game.IActionHandler;
import worms.model.programs.Factory;
import worms.model.programs.ParseOutcome;
import worms.model.programs.ProgramParser;
import worms.model.programs.expressions.Expression;
import worms.model.programs.statements.Statement;
import worms.model.programs.types.Type;

/**
 * Facade class connecting the code to the user interface.
 *
 * @author	Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * @version	3.0
 */
public class Facade implements IFacade {
	/******************************************************************************************************
	 * PART 1
	 *****************************************************************************************************/
	/*
	 * part1
	 * 
	 * @Override public Worm createWorm(double x, double y, double direction,
	 * double radius, String name) { return new Worm(x, y, direction, radius,
	 * name); }
	 * 
	 * @Override public boolean canMove(Worm worm, int nbSteps) { return
	 * worm.canMove(nbSteps); }
	 * 
	 * @Override public void move(Worm worm, int nbSteps) { worm.move(nbSteps);
	 * }
	 */
	@Override
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	@Override
	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	}

	/*
	 * part1
	 * 
	 * @Override public void jump(Worm worm) { worm.jump(); }
	 * 
	 * @Override public double getJumpTime(Worm worm) { return worm.jumpTime();
	 * }
	 */

	@Override
	public double[] getJumpStep(Worm worm, double t) {
		return worm.jumpStep(t);
	}

	@Override
	public double getX(Worm worm) {
		return worm.getXCoordinate();
	}

	@Override
	public double getY(Worm worm) {
		return worm.getYCoordinate();
	}

	@Override
	public double getOrientation(Worm worm) {
		return worm.getOrientation();
	}

	@Override
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	@Override
	public void setRadius(Worm worm, double newRadius) {
		worm.setRadius(newRadius);
	}

	@SuppressWarnings("static-access")
	@Override
	public double getMinimalRadius(Worm worm) {
		return Worm.getMinimalRadius();
	}

	@Override
	public int getActionPoints(Worm worm) {
		return worm.getActionPoints();
	}

	@Override
	public int getMaxActionPoints(Worm worm) {
		return worm.getMaxActionPoints();
	}

	@Override
	public String getName(Worm worm) {
		return worm.getName();
	}

	@Override
	public void rename(Worm worm, String newName) {
		worm.setName(newName);
	}

	@Override
	public double getMass(Worm worm) {
		return worm.getMass();
	}

	/******************************************************************************************************
	 * PART 2
	 *****************************************************************************************************/
	@Override
	public void addEmptyTeam(World world, String newName) {
		world.addEmptyTeam(newName);
	}

	@Override
	public void addNewFood(World world) {
		world.addNewFood();
	}

	// @Override
	// public void addNewWorm(World world) {
	// world.addNewWorm();
	// }

	@Override
	public boolean canFall(Worm worm) {
		return worm.canFall();
	}

	@Override
	public boolean canMove(Worm worm) {
		return worm.canMove();
	}

	@Override
	public Food createFood(World world, double x, double y) {
		return world.createFood(x, y);
	}

	@Override
	public World createWorld(double width, double height,
			boolean[][] passableMap, Random random) {
		World w = new World(width, height, passableMap, random);
		return w;
	}

	// @Override
	// public Worm createWorm(World world, double x, double y, double direction,
	// double radius, String name) {
	// Worm w = world.createWorm(x, y, direction, radius, name);
	// return w;
	// }

	@Override
	public void fall(Worm worm) {
		worm.fall();
	}

	@Override
	public Projectile getActiveProjectile(World world) {
		return world.getActiveProjectile();
	}

	@Override
	public Worm getCurrentWorm(World world) {
		return world.getCurrentWorm();
	}

	@Override
	public Collection<Food> getFood(World world) {
		return world.getFoods();
	}

	@Override
	public int getHitPoints(Worm worm) {
		return worm.getHitPoints();
	}

	@Override
	public double[] getJumpStep(Projectile projectile, double t) {
		return projectile.getJumpStep(t);
	}

	@Override
	public double getJumpTime(Projectile projectile, double timeStep) {
		return projectile.getJumpTime(timeStep);
	}

	@Override
	public double getJumpTime(Worm worm, double timeStep) {
		return worm.jumpTime(timeStep);
	}

	@Override
	public int getMaxHitPoints(Worm worm) {
		return worm.getMaxHitPoints();
	}

	@Override
	public double getRadius(Food food) {
		return food.getRadius();
	}

	@Override
	public double getRadius(Projectile projectile) {
		return projectile.getRadius();
	}

	@Override
	public String getSelectedWeapon(Worm worm) {
		return worm.getSelectedWeapon();
	}

	@Override
	public String getTeamName(Worm worm) {
		return worm.getTeamName();
	}

	@Override
	public String getWinner(World world) {
		return world.getWinner();
	}

	@Override
	public Collection<Worm> getWorms(World world) {
		return world.getWorms();
	}

	@Override
	public double getX(Food food) {
		return food.getXCoordinate();
	}

	@Override
	public double getX(Projectile projectile) {
		return projectile.getXCoordinate();
	}

	@Override
	public double getY(Food food) {
		return food.getYCoordinate();
	}

	@Override
	public double getY(Projectile projectile) {
		return projectile.getYCoordinate();
	}

	@Override
	public boolean isActive(Food food) {
		ArrayList<Worm> worms = new ArrayList<>();
		for (Worm w : food.getWorld().getWorms()) {
			worms.add(w);
		}
		for (Worm w : worms) {
			w.checkIfWormCanEat();
		}
		return food.isActive();
	}

	@Override
	public boolean isActive(Projectile projectile) {
		return projectile.isActive();
	}

	@Override
	public boolean isAdjacent(World world, double x, double y, double radius) {
		return world.isAdjacent(x, y, radius);
	}

	@Override
	public boolean isAlive(Worm worm) {
		return worm.isAlive();
	}

	@Override
	public boolean isGameFinished(World world) {
		return world.isGameFinished();
	}

	@Override
	public boolean isImpassable(World world, double x, double y, double radius) {
		return world.isImpassable(x, y, radius);
	}

	@Override
	public void jump(Projectile projectile, double timeStep) {
		projectile.launch(timeStep);
	}

	@Override
	public void jump(Worm worm, double timeStep) {
		worm.jump(timeStep);
	}

	@Override
	public void move(Worm worm) {
		worm.move();
	}

	@Override
	public void selectNextWeapon(Worm worm) {
		worm.selectNextWeapon();
	}

	@Override
	public void shoot(Worm worm, int yield) {
		worm.shoot(yield);
	}

	@Override
	public void startGame(World world) {
		world.startGame();
	}

	@Override
	public void startNextTurn(World world) {
		world.startNextTurn();
	}

	/******************************************************************************************************
	 * PART 3
	 *****************************************************************************************************/
	@Override
	public void addNewWorm(World world, Program program) {
		world.addNewWorm(program);
	}

	@Override
	public Worm createWorm(World world, double x, double y, double direction,
			double radius, String name, Program program) {
		Worm w = world.createWorm(x, y, direction, radius, name, program);
		return w;
	}

	@Override
	public ParseOutcome<?> parseProgram(String programText,
			IActionHandler handler) {
		Factory f = new Factory();
		ProgramParser<Expression, Statement, Type> pp = new ProgramParser<>(f);
		pp.parse(programText);
		Program p = new Program(programText, handler,
				(Statement) pp.getStatement());
		if (pp.getErrors().isEmpty()) {
			return ParseOutcome.success(p);
		} else {
			return ParseOutcome.failure(pp.getErrors());
		}
	}

	@Override
	public boolean hasProgram(Worm worm) {
		return worm.hasProgram();
	}

	@Override
	public boolean isWellFormed(Program program) {
		return program.isWellFormed();
	}

}
