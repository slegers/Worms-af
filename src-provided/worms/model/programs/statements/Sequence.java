package worms.model.programs.statements;

import java.util.List;

import worms.model.Program;
import worms.util.Util;

public class Sequence extends Statement {
	private List<Statement> statements;
	private int counter;

	public Sequence(List<Statement> statements) {
		this.statements = statements;
	}

	@Override
	public boolean execute(Program program) {
		if (counter == statements.size()) {
			counter = 0;
		}
		while (counter < statements.size()) {
			if (program.getInstructionNumber() >= Util.programMaximum) {
				return false;
			}
			statements.get(counter).execute(program);
			counter++;
		}
		super.postExecute(program);
		return true;
	}

	@Override
	public boolean isWellFormed() {
		for (Statement statement : statements) {
			if (!statement.isWellFormed()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean containsAction() {
		for (Statement statement : statements) {
			if (statement.containsAction()) {
				return true;
			}
		}
		return false;
	}

}
