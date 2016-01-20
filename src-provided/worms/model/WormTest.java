package worms.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import worms.gui.game.IActionHandler;
import worms.model.programs.Factory;
import worms.model.programs.ProgramParser;
import worms.model.programs.expressions.Expression;
import worms.model.programs.statements.Statement;
import worms.model.programs.types.Type;
import worms.util.Util;

/**
 * A class to test all methods of the class worms.
 *
 * @author 	Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * @version	3.0
 *
 * https://github.com/hazzarux/worms.git
 */
public class WormTest {
	/******************************************************************************************************
	 * PART 1
	 *****************************************************************************************************/
	@Test
	public void constructorTest() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertTrue(Util.fuzzyEquals(1, w.getXCoordinate()));
		assertTrue(Util.fuzzyEquals(4, w.getYCoordinate()));
		assertTrue(Util.fuzzyEquals(0.64, w.getOrientation()));
		assertEquals("Tester", w.getName());
	}

	@Test
	public void isValidRadius_legal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertTrue(w.canHaveAsRadius(w.getRadius()));
	}

	@Test
	public void isValidRadius_illegal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertEquals(false, w.canHaveAsRadius(0.10));
	}

	@Test
	public void isValidName_legal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		String name = "James o'Hara";
		assertTrue(w.canHaveAsName(name));
	}

	@Test
	public void isValidName_illegal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		String name = "#James";
		assertEquals(false, w.canHaveAsName(name));
	}

	@Test
	public void isValidOrientation_legal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertTrue(w.canHaveAsOrientation(w.getOrientation()));
	}

	@Test
	public void isValidOrientation_illegal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertEquals(false, w.canHaveAsOrientation(2 * Math.PI + 2));
		assertEquals(false, w.canHaveAsOrientation(-2 * Math.PI - 2));
	}

	@Test
	public void isValidNumberOfSteps_legal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertTrue(w.canHaveAsNumberOfSteps(2));
	}

	@Test
	public void isValidNumberOfSteps_illegal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertEquals(false, w.canHaveAsNumberOfSteps(-1));
	}

	@Test
	public void hasEnoughActionPoints_legal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertTrue(w.hasEnoughActionPoints(w.getActionPoints() - 1));
	}

	@Test
	public void hasEnoughActionPoints_illegal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertEquals(false, w.hasEnoughActionPoints(w.getActionPoints() + 1));
	}

	@Test
	public void canTurn_legal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertTrue(w.canTurn(0.2));
	}

	@Test
	public void canTurn_illegal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		assertEquals(false, w.canTurn(2 * Math.PI + 2));
	}

	@Test
	public void wormIsFacingDownwards_legal() {
		Worm w = new Worm(1.0, 4.0, Math.PI + 1, 1.0, "Tester");
		assertTrue(w.wormIsFacingDownwards());
	}

	@Test
	public void wormIsFacingDownwards_Illegal() {
		Worm w = new Worm(1.0, 4.0, 0.2, 1.0, "Tester");
		assertEquals(false, w.wormIsFacingDownwards());
	}

	// @SuppressWarnings("static-access")
	// @Test
	// public void calculateMass() {
	// Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
	// assertTrue(Util.fuzzyEquals(4448.495197,
	// w.calculateMass(w.getDensity(), w.getRadius())));
	// assertEquals(
	// false,
	// Util.fuzzyEquals(2.0,
	// w.calculateMass(w.getDensity(), w.getRadius())));
	// }

	@Test
	public void setRadius_legal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		w.setRadius(2.0);
		assertTrue(Util.fuzzyEquals(2.0, w.getRadius()));
	}

	@Test(expected = ModelException.class)
	public void setRadius_illegal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		w.setRadius(0.1);
	}

	// @SuppressWarnings("static-access")
	// @Test
	// public void getMass() {
	// Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
	// double mass = w.calculateMass(w.getDensity(), w.getRadius());
	// assertTrue(mass == w.getMass());
	// }

	@Test
	public void setName_legal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		String name = "James o'Hara";
		w.setName(name);
		assertTrue(name == w.getName());
	}

	@Test(expected = ModelException.class)
	public void setName_illegal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		String name = "James#";
		w.setName(name);
	}

	@Test
	public void turn_legal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		w.turn(0.8);
		assertTrue(Util.fuzzyEquals(1.44, w.getOrientation()));
	}

	@Test
	public void turn_illegal() {
		Worm w = new Worm(1.0, 4.0, 0.64, 1.0, "Tester");
		w.turn(50);
		assertEquals(false, Util.fuzzyEquals(50.64, w.getOrientation()));
	}

	/******************************************************************************************************
	 * PART 2
	 *****************************************************************************************************/
	/**
	 * World
	 */
	private Random random = new Random();

	// X X X X
	// . . . .
	// . . . .
	// X X X X
	private boolean[][] passableMap = new boolean[][] {
			{ false, false, false, false }, { true, true, true, true },
			{ true, true, true, true }, { false, false, false, false } };

	@Test
	public void constructorWorld() {
		World w = new World(4.0, 4.0, passableMap, random);
		assertTrue(w.getClass() == World.class);
	}

	@Test
	public void addEmptyTeam_legal() {
		World w = new World(4.0, 4.0, passableMap, random);
		w.addEmptyTeam("LegalTeamName");
	}

	@Test(expected = ModelException.class)
	public void addEmptyTeam_illegal() {
		World w = new World(4.0, 4.0, passableMap, random);
		w.addEmptyTeam("illegalname");
	}

	@Test
	public void createFood() {
		World w = new World(4.0, 4.0, passableMap, random);
		w.createFood(2, 2);
	}

	// @Test
	// public void createWorm() {
	// World world = new World(4.0, 4.0, passableMap, random);
	// @SuppressWarnings("unused")
	// Worm worm = world.createWorm(2, 2, 0.6, 1, "Wally");
	// }

	/**
	 * Worm
	 */
	private static final double EPS = Util.DEFAULT_EPSILON;

	@Test
	public void fall() {
		// . X .
		// . w .
		// . . .
		// X X X
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = new Worm(world, 1, 1, -Math.PI / 2, 0.5, "Wally");
		assertTrue(worm.canFall());
		worm.fall();
		assertEquals(1.0, worm.getXCoordinate(), EPS);
		assertEquals(1.0, worm.getYCoordinate(), EPS);
	}

	@Test
	public void jumpTime() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		assertEquals(0.11999999999999998, worm.jumpTime(1e-2), EPS);
	}

	@Test
	public void getMaxHitPoints() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		assertEquals(556, worm.getMaxHitPoints(), EPS);
	}

	@Test
	public void getSelectedWeapon() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		assertTrue(worm.getSelectedWeapon().equals("Bazooka")
				|| worm.getSelectedWeapon().equals("The Rifle"));
	}

	@Test
	public void getTeamName() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		assertTrue(worm.getTeamName().equals("No team"));
	}

	@Test
	public void jump() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		worm.jump(1e-2);
		assertEquals(1.5, worm.getXCoordinate(), EPS);
		assertEquals(1.5410265153929026, worm.getYCoordinate(), EPS);
	}

	@Test(expected = ModelException.class)
	public void move() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		worm.move();
	}

	@Test
	public void selectNextWeapon() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		assertTrue(worm.getSelectedWeapon().equals("The Rifle"));
		worm.selectNextWeapon();
		assertTrue(worm.getSelectedWeapon().equals("Bazooka"));
	}

	@Test
	public void shoot() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		assertTrue(worm.getActiveProjectile() == null);
		worm.shoot(50);
	}

	@Test
	public void canFall() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = new Worm(world, 1, 1, -Math.PI / 2, 0.5, "Wally");
		assertTrue(worm.canFall());
	}

	@Test
	public void canMove() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm w = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		assertFalse(w.canMove());
	}

	@Test
	public void getTeam() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm w = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		assertTrue(w.getTeam() == null);
	}

	@Test
	public void setTeam() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm w = new Worm(world, 1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		Team t = new Team("Testteam");
		w.setTeam(t);
		assertTrue(w.getTeam() == t);
	}

	/******************************************************************************************************
	 * PART 3
	 *****************************************************************************************************/
	@Test
	public void deduceHP() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm w = world.createWorm(1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		int hp = w.getHitPoints();
		w.deduceHitPoints(1);
		assertTrue(w.getHitPoints() == hp - 1);
	}

	@Test
	public void isInside() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		assertTrue(world.isInside(2, 2));
		assertTrue(!world.isInside(5, 5));
	}

	@Test
	public void setActiveProjectile() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm w = world.createWorm(1.5, 2.5, -Math.PI / 2, 0.5, "Wally");
		Bazooka b = new Bazooka(w, world);
		Projectile p = new Projectile(w, world, 2, 2, -Math.PI / 2, 5, b);
		p.setActive(true);
		assertTrue(p.isActive());
	}

	@Test
	public void addNewWorm() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Facade f = new Facade();
		IActionHandler handler = new SimpleActionHandler(f);
		Factory fa = new Factory();
		ProgramParser<Expression, Statement, Type> pp = new ProgramParser<>(fa);
		Statement s = pp.getStatement();
		Program p = new Program(
				"double x; while (x < 1.5) {\nx := x + 0.1;\n}\n turn x;",
				handler, s);
		world.addNewWorm(p);
		assertTrue(world.getWorms().size() == 1);
	}

	@Test
	public void createWorm() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Facade f = new Facade();
		IActionHandler handler = new SimpleActionHandler(f);
		Factory fa = new Factory();
		ProgramParser<Expression, Statement, Type> pp = new ProgramParser<>(fa);
		Statement s = pp.getStatement();
		Program p = new Program(
				"double x; while (x < 1.5) {\nx := x + 0.1;\n}\n turn x;",
				handler, s);
		Worm w = world.createWorm(1.5, 2.5, -Math.PI / 2, 0.5, "Wally", p);
		assertTrue(w.getWorld() == world);
	}

	@Test
	public void hasProgram() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Facade f = new Facade();
		IActionHandler handler = new SimpleActionHandler(f);
		Factory fa = new Factory();
		ProgramParser<Expression, Statement, Type> pp = new ProgramParser<>(fa);
		Statement s = pp.getStatement();
		Program p = new Program(
				"double x; while (x < 1.5) {\nx := x + 0.1;\n}\n turn x;",
				handler, s);
		Worm w = world.createWorm(1.5, 2.5, -Math.PI / 2, 0.5, "Wally", p);
		assertTrue(w.hasProgram());
	}

	@Test
	public void isWellFormed() {
		Facade f = new Facade();
		IActionHandler handler = new SimpleActionHandler(f);
		Factory fa = new Factory();
		ProgramParser<Expression, Statement, Type> pp = new ProgramParser<>(fa);
		Statement s = pp.getStatement();
		Program p = new Program(
				"double x; while (x < 1.5) {\nx := x + 0.1;\n}\n turn x;",
				handler, s);
		assertTrue(p.isWellFormed());
	}
}
