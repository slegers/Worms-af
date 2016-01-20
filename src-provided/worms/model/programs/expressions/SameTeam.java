package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.Worm;
import worms.model.programs.types.BooleanType;
import worms.model.programs.types.EntityType;

public class SameTeam extends Expression {
	private Expression e;

	public SameTeam(Expression e) {
		this.e = e;
	}

	@Override
	public Expression getValue() {
		return this.e;
	}

	public BooleanLiteral getResult(Program p) {
		EntityType<Worm> ew = (EntityType<Worm>) e.getValue();
		Worm w = ew.getValue();

		if (p.getWorm().getTeam() == null) {
			return new BooleanLiteral(new BooleanType(false));
		}
		return new BooleanLiteral(new BooleanType(w.getTeam() == p.getWorm()
				.getTeam()));
	}

	@Override
	public Object getResult() {
		return null;
	}

}
