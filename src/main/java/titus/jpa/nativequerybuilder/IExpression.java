package titus.jpa.nativequerybuilder;

import java.util.Collection;

import titus.jpa.nativequerybuilder.impl.Expression;

public interface IExpression extends INativeSql {

	public static IExpressionBuilder builder() {
		return Expression.builder();
	}

	public static interface IExpressionBuilder {
		IExpressionOperaterStage column(IColumn anColumn);

		IExpressionOperaterStage column(String anColumn);

		IExpressionOperaterStage function(ISqlFunction aFunction);
	}

	public static interface IExpressionOperaterStage {
		IExpressionFinalStage eq();

		IExpressionFinalStage neq();

		IExpressionFinalStage gt();

		IExpressionFinalStage gte();

		IExpressionFinalStage lt();

		IExpressionFinalStage lte();

		IExpressionFinalStage any();

		IExpressionFinalStage in();

		IExpressionFinalStage like();

		IExpressionFinalStage ilike();

		IExpressionFinalStage fts();

		IExpressionFinalStage all();

		IExpressionFinalStage some();

		IExpressionFinalStage exists();

		IExpressionFinalStage customOperator(IOperator aOperator);

	}

	public static interface IExpressionFinalStage {
		IBuilder<IExpression> value(Object aValue);

		IBuilder<IExpression> values(Object[] aValues);

		IBuilder<IExpression> values(Collection<?> aValues);

		IBuilder<IExpression> variable(String aName);

		IBuilder<IExpression> column(IColumn anColumn);

		IBuilder<IExpression> function(ISqlFunction aFunction);

		IBuilder<IExpression> subSelect(ISubSelect aSubSelect);

	}

}
