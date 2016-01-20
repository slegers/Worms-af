package worms.model.programs.statements;

import worms.model.Program;

public abstract class Statement {
	public abstract boolean execute(Program program);

	public abstract boolean isWellFormed();

	public void postExecute(Program program) {
		program.setInstructionNumber(program.getInstructionNumber() + 1);
		if (program.getInstructionNumber() >= 1000) {
			setCycleFinished(true);
		}
	}

	public boolean isCycleFinished() {
		return this.cycleFinished;
	}

	public void setCycleFinished(boolean cycleFinished) {
		this.cycleFinished = cycleFinished;
	}

	private boolean cycleFinished;

	public abstract boolean containsAction();
}
