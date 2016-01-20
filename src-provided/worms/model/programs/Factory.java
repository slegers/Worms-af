package worms.model.programs;

import java.util.List;

import worms.model.programs.expressions.Add;
import worms.model.programs.expressions.And;
import worms.model.programs.expressions.BooleanLiteral;
import worms.model.programs.expressions.Cos;
import worms.model.programs.expressions.Division;
import worms.model.programs.expressions.DoubleLiteral;
import worms.model.programs.expressions.Equality;
import worms.model.programs.expressions.Expression;
import worms.model.programs.expressions.GetAP;
import worms.model.programs.expressions.GetDir;
import worms.model.programs.expressions.GetHP;
import worms.model.programs.expressions.GetMaxAP;
import worms.model.programs.expressions.GetMaxHP;
import worms.model.programs.expressions.GetRadius;
import worms.model.programs.expressions.GetX;
import worms.model.programs.expressions.GetY;
import worms.model.programs.expressions.GreaterThan;
import worms.model.programs.expressions.GreaterThanOrEqualTo;
import worms.model.programs.expressions.Inequality;
import worms.model.programs.expressions.IsFood;
import worms.model.programs.expressions.IsWorm;
import worms.model.programs.expressions.LessThan;
import worms.model.programs.expressions.LessThanOrEqualTo;
import worms.model.programs.expressions.Multiplication;
import worms.model.programs.expressions.Not;
import worms.model.programs.expressions.NullLiteral;
import worms.model.programs.expressions.Or;
import worms.model.programs.expressions.SameTeam;
import worms.model.programs.expressions.SearchObj;
import worms.model.programs.expressions.SelfExpression;
import worms.model.programs.expressions.Sin;
import worms.model.programs.expressions.Sqrt;
import worms.model.programs.expressions.Subtraction;
import worms.model.programs.expressions.VariableAccess;
import worms.model.programs.statements.Assignment;
import worms.model.programs.statements.Fire;
import worms.model.programs.statements.ForEach;
import worms.model.programs.statements.If;
import worms.model.programs.statements.Jump;
import worms.model.programs.statements.Move;
import worms.model.programs.statements.Print;
import worms.model.programs.statements.Sequence;
import worms.model.programs.statements.Skip;
import worms.model.programs.statements.Statement;
import worms.model.programs.statements.ToggleWeap;
import worms.model.programs.statements.Turn;
import worms.model.programs.statements.While;
import worms.model.programs.types.BooleanType;
import worms.model.programs.types.DoubleType;
import worms.model.programs.types.EntityType;
import worms.model.programs.types.Type;

public class Factory implements ProgramFactory<Expression, Statement, Type> {

	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		return new DoubleLiteral(new DoubleType(d));
	}

	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		return new BooleanLiteral(new BooleanType(b));
	}

	@Override
	public Expression createAnd(int line, int column, Expression e1,
			Expression e2) {
		return new And(e1, e2);
	}

	@Override
	public Expression createOr(int line, int column, Expression e1,
			Expression e2) {
		return new Or(e1, e2);
	}

	@Override
	public Expression createNot(int line, int column, Expression e) {
		return new Not(e);
	}

	@Override
	public Expression createNull(int line, int column) {
		return new NullLiteral();
	}

	@Override
	public Expression createSelf(int line, int column) {
		return new SelfExpression();
	}

	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return new GetX(e);
	}

	@Override
	public Expression createGetY(int line, int column, Expression e) {
		return new GetY(e);
	}

	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new GetRadius(e);
	}

	@Override
	public Expression createGetDir(int line, int column, Expression e) {
		return new GetDir(e);
	}

	@Override
	public Expression createGetAP(int line, int column, Expression e) {
		return new GetAP(e);
	}

	@Override
	public Expression createGetMaxAP(int line, int column, Expression e) {
		return new GetMaxAP(e);
	}

	@Override
	public Expression createGetHP(int line, int column, Expression e) {
		return new GetHP(e);
	}

	@Override
	public Expression createGetMaxHP(int line, int column, Expression e) {
		return new GetMaxHP(e);
	}

	@Override
	public Expression createSameTeam(int line, int column, Expression e) {
		return new SameTeam(e);
	}

	@Override
	public Expression createSearchObj(int line, int column, Expression e) {
		return new SearchObj(e);
	}

	@Override
	public Expression createIsWorm(int line, int column, Expression e) {
		return new IsWorm(e);
	}

	@Override
	public Expression createIsFood(int line, int column, Expression e) {
		return new IsFood(e);
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name) {
		return new VariableAccess(name);
	}

	@Override
	public Expression createVariableAccess(int line, int column, String name,
			Type type) {
		return new VariableAccess(name, type);
	}

	@Override
	public Expression createLessThan(int line, int column, Expression e1,
			Expression e2) {
		return new LessThan(e1, e2);
	}

	@Override
	public Expression createGreaterThan(int line, int column, Expression e1,
			Expression e2) {
		return new GreaterThan(e1, e2);
	}

	@Override
	public Expression createLessThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new LessThanOrEqualTo(e1, e2);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column,
			Expression e1, Expression e2) {
		return new GreaterThanOrEqualTo(e1, e2);
	}

	@Override
	public Expression createEquality(int line, int column, Expression e1,
			Expression e2) {
		return new Equality(e1, e2);
	}

	@Override
	public Expression createInequality(int line, int column, Expression e1,
			Expression e2) {
		return new Inequality(e1, e2);
	}

	@Override
	public Expression createAdd(int line, int column, Expression e1,
			Expression e2) {
		return new Add(e1, e2);
	}

	@Override
	public Expression createSubtraction(int line, int column, Expression e1,
			Expression e2) {
		return new Subtraction(e1, e2);
	}

	@Override
	public Expression createMul(int line, int column, Expression e1,
			Expression e2) {
		return new Multiplication(e1, e2);
	}

	@Override
	public Expression createDivision(int line, int column, Expression e1,
			Expression e2) {
		return new Division(e1, e2);
	}

	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		return new Sqrt(e);
	}

	@Override
	public Expression createSin(int line, int column, Expression e) {
		return new Sin(e);
	}

	@Override
	public Expression createCos(int line, int column, Expression e) {
		return new Cos(e);
	}

	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new Turn(angle);
	}

	@Override
	public Statement createMove(int line, int column) {
		return new Move();
	}

	@Override
	public Statement createJump(int line, int column) {
		return new Jump();
	}

	@Override
	public Statement createToggleWeap(int line, int column) {
		return new ToggleWeap();
	}

	@Override
	public Statement createFire(int line, int column, Expression yield) {
		return new Fire(yield);
	}

	@Override
	public Statement createSkip(int line, int column) {
		return new Skip();
	}

	@Override
	public Statement createAssignment(int line, int column,
			String variableName, Expression rhs) {
		return new Assignment(variableName, rhs);
	}

	@Override
	public Statement createIf(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		return new If(condition, then, otherwise);
	}

	@Override
	public Statement createWhile(int line, int column, Expression condition,
			Statement body) {
		return new While(condition, body);
	}

	@Override
	public Statement createForeach(int line, int column,
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		return new ForEach(type, variableName, body);
	}

	@Override
	public Statement createSequence(int line, int column,
			List<Statement> statements) {
		return new Sequence(statements);
	}

	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new Print(e);
	}

	@Override
	public Type createDoubleType() {
		return new DoubleType();
	}

	@Override
	public Type createBooleanType() {
		return new BooleanType();
	}

	@Override
	public Type createEntityType() {
		return new EntityType<>();
	}

}
