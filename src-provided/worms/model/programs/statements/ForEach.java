package worms.model.programs.statements;

import worms.model.Food;
import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.programs.expressions.EntityLiteral;
import worms.model.programs.types.EntityType;
import worms.util.Util;

public class ForEach extends Statement {
	private worms.model.programs.ProgramFactory.ForeachType type;
	private String variableName;
	private Statement body;

	public ForEach(worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		this.type = type;
		this.variableName = variableName;
		this.body = body;
	}

	private boolean run = false;

	@Override
	public boolean execute(Program program) {
		if (type == ForeachType.WORM) {
			EntityType<Worm> e = new EntityType<>();
			for (Worm w : program.getWorm().getWorld().getWorms()) {
				if (program.getInstructionNumber() >= Util.programMaximum) {
					return false;
				}
				e.setValue(w);
				program.addGlobal(variableName, new EntityLiteral<>(e));
				body.execute(program);
			}
		} else if (type == ForeachType.FOOD) {
			EntityType<Food> e = new EntityType<>();
			for (Food f : program.getWorm().getWorld().getFoods()) {
				if (program.getInstructionNumber() >= Util.programMaximum) {
					return false;
				}
				e.setValue(f);
				program.addGlobal(variableName, new EntityLiteral<>(e));
				body.execute(program);
			}
		} else if (type == ForeachType.ANY) {
			EntityType<Worm> a = new EntityType<>();
			EntityType<Food> b = new EntityType<>();
			for (Worm w : program.getWorm().getWorld().getWorms()) {
				if (program.getInstructionNumber() >= Util.programMaximum) {
					return false;
				}
				a.setValue(w);
				program.addGlobal(variableName, new EntityLiteral<>(a));
				body.execute(program);
			}
			for (Food f : program.getWorm().getWorld().getFoods()) {
				if (program.getInstructionNumber() >= Util.programMaximum) {
					return false;
				}
				b.setValue(f);
				program.addGlobal(variableName, new EntityLiteral<>(b));
			}
		}
		super.postExecute(program);
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return (!body.containsAction());
	}

	@Override
	public boolean containsAction() {
		return body.containsAction();
	}
}
