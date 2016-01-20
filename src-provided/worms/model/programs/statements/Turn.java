package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.DoubleLiteral;
import worms.model.programs.expressions.Expression;

public class Turn extends Statement {
	private Expression angle;

	public Turn(Expression angle) {
		this.angle = angle;
	}

	@Override
	public boolean execute(Program program) {
		DoubleLiteral dl = (DoubleLiteral) angle;
		Double d = dl.getResult();
		if (!program.getWorm().canTurn(d)) {
			return false;
		}
		program.getHandler().turn(program.getWorm(), d);
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
