package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.DoubleLiteral;
import worms.model.programs.expressions.Expression;

public class Fire extends Statement {
	private DoubleLiteral yield;

	public Fire(Expression yield) {
		this.yield = (DoubleLiteral) yield;
	}

	@Override
	public boolean execute(Program program) {
		if (!program.getWorm().canShoot()) {
			return false;
		}
		program.getHandler().fire(program.getWorm(), yield.getValue().toInt());
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
