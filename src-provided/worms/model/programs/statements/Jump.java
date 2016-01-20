package worms.model.programs.statements;

import worms.model.Program;

public class Jump extends Statement {

	@Override
	public boolean execute(Program program) {
		if (!program.getWorm().canJump()) {
			return false;
		}
		program.getHandler().jump(program.getWorm());
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
