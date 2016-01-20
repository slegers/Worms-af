package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.Expression;

public class Assignment extends Statement {
	private String variableName;
	private Expression rhs;

	public Assignment(String variableName, Expression rhs) {
		this.variableName = variableName;
		this.rhs = rhs;
	}

	@Override
	public boolean execute(Program program) {
		program.addGlobal(variableName, rhs);
		super.postExecute(program);
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean containsAction() {
		return false;
	}
}
