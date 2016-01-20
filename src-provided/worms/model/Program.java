package worms.model;

import java.util.HashMap;

import worms.gui.game.IActionHandler;
import worms.model.programs.expressions.BooleanLiteral;
import worms.model.programs.expressions.DoubleLiteral;
import worms.model.programs.expressions.EntityLiteral;
import worms.model.programs.expressions.Expression;
import worms.model.programs.statements.Statement;

/**
 * A class that represents a program.
 * 
 * 	@author		Yanice SLEGERS (1ste bachelor informatica, r0456491), Fevzi Yigit OZKAN (1ste bachelor informatica, r0456142)
 * 	@version 	3.0
 *
 * https://github.com/hazzarux/worms.git
 *
 */
public class Program {
	private int instructionNumber;
	private Worm worm;

	public Program(String programText2, IActionHandler handler2,
			Statement statement2) {
		setProgramText(programText2);
		setHandler(handler2);
		setStatement(statement2);
	}

	public Worm getWorm() {
		return worm;
	}

	public void setWorm(Worm worm) {
		this.worm = worm;
	}

	public boolean isWellFormed() {
		if (statement == null) {
			return true;
		}
		return statement.isWellFormed();
	}

	private String programText;
	private IActionHandler handler;

	public String getProgramText() {
		return programText;
	}

	public void setProgramText(String programText) {
		this.programText = programText;
	}

	public IActionHandler getHandler() {
		return handler;
	}

	public void setHandler(IActionHandler handler) {
		this.handler = handler;
	}

	private Statement statement;

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public int getInstructionNumber() {
		return instructionNumber;
	}

	public HashMap<String, DoubleLiteral> getDoubles() {
		return doubles;
	}

	public void setDoubles(HashMap<String, DoubleLiteral> doubles) {
		this.doubles = doubles;
	}

	public HashMap<String, BooleanLiteral> getBooleans() {
		return booleans;
	}

	public void setBooleans(HashMap<String, BooleanLiteral> booleans) {
		this.booleans = booleans;
	}

	private HashMap<String, DoubleLiteral> doubles = new HashMap<>();
	private HashMap<String, BooleanLiteral> booleans = new HashMap<>();
	private HashMap<String, EntityLiteral<?>> entities = new HashMap<>();

	public HashMap<String, EntityLiteral<?>> getEntities() {
		return entities;
	}

	public void setEntities(HashMap<String, EntityLiteral<?>> entities) {
		this.entities = entities;
	}

	public void setInstructionNumber(int instructionNumber) {
		this.instructionNumber = instructionNumber;
	}

	public void addGlobal(String variableName, Expression rhs) {
		if (rhs instanceof DoubleLiteral && !booleans.containsKey(variableName)
				&& !entities.containsKey(variableName)) {
			doubles.put(variableName, (DoubleLiteral) rhs);
		} else if (rhs instanceof EntityLiteral<?>
				&& !doubles.containsKey(variableName)
				&& !booleans.containsKey(variableName)) {
			entities.put(variableName, (EntityLiteral<?>) rhs);
		} else if (rhs instanceof BooleanLiteral
				&& !entities.containsKey(variableName)
				&& !doubles.containsKey(variableName)) {
			booleans.put(variableName, (BooleanLiteral) rhs);
		}
	}

	public void execute(Worm w) {
		setWorm(w);
		getStatement().execute(this);
	}
}
