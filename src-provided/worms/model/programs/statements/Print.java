package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.Expression;
import worms.util.Util;

public class Print extends Statement {
	private Expression e;

	public Print(Expression e) {
		this.e = e;
	}

	@Override
	public boolean execute(Program program) {
		if (program.getInstructionNumber() >= Util.programMaximum) {
			return false;
		}
		System.out.println(e.getValue());
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
