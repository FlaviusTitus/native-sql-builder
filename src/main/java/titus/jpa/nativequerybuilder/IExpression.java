package titus.jpa.nativequerybuilder;

import java.util.Collection;

import titus.jpa.nativequerybuilder.impl.Expression;

/**
 * The Interface IExpression.
 */
public interface IExpression extends INativeSql {

	/**
	 * Builder.
	 *
	 * @return the i expression builder
	 */
	public static IExpressionBuilder builder() {
		return Expression.builder();
	}

	/**
	 * The Interface IExpressionBuilder.
	 */
	public static interface IExpressionBuilder {

		/**
		 * Column.
		 *
		 * @param anColumn the an column
		 * @return the i expression operater stage
		 */
		IExpressionOperaterStage column(IColumn anColumn);

		/**
		 * Column.
		 *
		 * @param anColumn the an column
		 * @return the i expression operater stage
		 */
		IExpressionOperaterStage column(String anColumn);

		/**
		 * Function.
		 *
		 * @param aFunction the a function
		 * @return the i expression operater stage
		 */
		IExpressionOperaterStage function(ISqlFunction aFunction);
	}

	/**
	 * The Interface IExpressionOperaterStage.
	 */
	public static interface IExpressionOperaterStage {

		/**
		 * Eq.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage eq();

		/**
		 * Neq.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage neq();

		/**
		 * Gt.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage gt();

		/**
		 * Gte.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage gte();

		/**
		 * Lt.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage lt();

		/**
		 * Lte.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage lte();

		/**
		 * Any.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage any();

		/**
		 * In.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage in();

		/**
		 * Like.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage like();

		/**
		 * Ilike.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage ilike();

		/**
		 * Fts.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage fts();

		/**
		 * All.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage all();

		/**
		 * Some.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage some();

		/**
		 * Exists.
		 *
		 * @return the i expression final stage
		 */
		IExpressionFinalStage exists();

		/**
		 * Custom operator.
		 *
		 * @param aOperator the a operator
		 * @return the i expression final stage
		 */
		IExpressionFinalStage customOperator(IOperator aOperator);

	}

	/**
	 * The Interface IExpressionFinalStage.
	 */
	public static interface IExpressionFinalStage {

		/**
		 * Value.
		 *
		 * @param aValue the a value
		 * @return the i builder
		 */
		IBuilder<IExpression> value(Object aValue);

		/**
		 * Values.
		 *
		 * @param aValues the a values
		 * @return the i builder
		 */
		IBuilder<IExpression> values(Object[] aValues);

		/**
		 * Values.
		 *
		 * @param aValues the a values
		 * @return the i builder
		 */
		IBuilder<IExpression> values(Collection<?> aValues);

		/**
		 * Variable.
		 *
		 * @param aName the a name
		 * @return the i builder
		 */
		IBuilder<IExpression> variable(String aName);

		/**
		 * Column.
		 *
		 * @param anColumn the an column
		 * @return the i builder
		 */
		IBuilder<IExpression> column(IColumn anColumn);

		/**
		 * Function.
		 *
		 * @param aFunction the a function
		 * @return the i builder
		 */
		IBuilder<IExpression> function(ISqlFunction aFunction);

		/**
		 * Sub select.
		 *
		 * @param aSubSelect the a sub select
		 * @return the i builder
		 */
		IBuilder<IExpression> subSelect(ISubSelect aSubSelect);

	}

}
