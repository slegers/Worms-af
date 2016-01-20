package worms.model.programs.statements;

import worms.model.Program;

public class Move extends Statement {

	@Override
	public boolean execute(Program program) {
		if (program.getWorm().canMove()) {
			return false;
		}
		program.getHandler().move(program.getWorm());
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
