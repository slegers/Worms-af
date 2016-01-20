package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.BooleanLiteral;
import worms.model.programs.expressions.Expression;

public class If extends Statement {
	private Expression condition;
	private Statement then, otherwise;

	public If(Expression condition, Statement then, Statement otherwise) {
		this.condition = condition;
		this.then = then;
		this.otherwise = otherwise;
	}

	private Statement notExecuted;

	@Override
	public boolean execute(Program program) {
		Boolean b = false;
		if (condition.getResult() instanceof BooleanLiteral) {
			b = ((BooleanLiteral) condition.getResult(program)).getResult();
		} else {
			b = (Boolean) condition.getResult(program);
		}
		if (notExecuted == null) {
			if (b) {
				if (then.execute(program) == false) {
					notExecuted = then;
					return false;
				}

			} else {
				if (otherwise.execute(program) == false) {
					notExecuted = otherwise;
					return false;
				}
			}
		} else {
			if (notExecuted.execute(program) == false) {
				return false;
			}
		}
		notExecuted = null;
		super.postExecute(program);
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return (then.isWellFormed() && otherwise.isWellFormed());
	}

	@Override
	public boolean containsAction() {
		return (then.containsAction() || otherwise.containsAction());
	}

}
