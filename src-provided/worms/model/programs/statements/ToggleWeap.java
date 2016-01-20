package worms.model.programs.statements;

import worms.model.Program;

public class ToggleWeap extends Statement {

	@Override
	public boolean execute(Program program) {
		program.getHandler().toggleWeapon(program.getWorm());
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
