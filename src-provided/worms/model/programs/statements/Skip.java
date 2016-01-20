package worms.model.programs.statements;

import worms.model.Program;

public class Skip extends Statement {

	@Override
	public boolean execute(Program program) {
		super.postExecute(program);
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

	@Override
	public boolean containsAction() {
		return true;
	}

}
