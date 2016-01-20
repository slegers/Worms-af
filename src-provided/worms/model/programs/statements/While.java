package worms.model.programs.statements;

import worms.model.Program;
import worms.model.programs.expressions.BooleanLiteral;
import worms.model.programs.expressions.Expression;

public class While extends Statement {
	private BooleanLiteral condition;
	private Statement body;

	public While(Expression condition, Statement body) {
		this.condition = (BooleanLiteral) condition.getResult();
		this.body = body;
	}

	private boolean finished;

	@Override
	public boolean execute(Program program) {
		finished = true;
		while (condition.getResult() == true && !super.isCycleFinished()
				&& finished) {
			finished = body.execute(program);
		}
		if (!finished) {
			return false;
		}
		super.postExecute(program);
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return body.isWellFormed();
	}

	@Override
	public boolean containsAction() {
		return body.containsAction();
	}

}
