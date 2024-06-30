package titus.sql.query.builder.impl;

import java.util.Collection;

import titus.sql.query.builder.IBuilder;
import titus.sql.query.builder.IColumn;
import titus.sql.query.builder.IExpression;
import titus.sql.query.builder.IOperator;
import titus.sql.query.builder.ISqlFunction;
import titus.sql.query.builder.ISubSelect;

/**
 * The Class Expression.
 */
public class Expression implements IExpression {

	/** The first. */
	private IExpression first;

	/** The operator. */
	private IOperator operator;

	/** The second. */
	private IExpression second;

	/** The native sql. */
	private String nativeSql;

	/**
	 * Instantiates a new expression.
	 *
	 * @param aFirst    the a first
	 * @param aOperator the a operator
	 * @param aSecond   the a second
	 */
	private Expression(final IExpression aFirst, final IOperator aOperator, final IExpression aSecond) {
		this.first = aFirst;
		this.operator = aOperator;
		this.second = aSecond;
	}

	/**
	 * Builds the native sql.
	 *
	 * @param anContext the an context
	 * @return the string
	 */
	@Override
	public String buildNativeSql(final INativeSqlBuildContext anContext) {
		if (this.nativeSql == null) {
			this.nativeSql = this.operator.buildNativSql(this.first, this.second, anContext);

			this.first = null;
			this.operator = null;
			this.second = null;
		}
		return this.nativeSql;
	}

	/**
	 * Builder.
	 *
	 * @return the i expression builder
	 */
	public static IExpressionBuilder builder() {
		return new ExpressionBuilder();
	}

	/**
	 * The Class ExpressionBuilder.
	 */
	public static final class ExpressionBuilder implements IExpressionBuilder {

		/**
		 * Column.
		 *
		 * @param aColumn the a column
		 * @return the i expression operater stage
		 */
		@Override
		public IExpressionOperaterStage column(final IColumn aColumn) {
			return new ExpressionOperaterStage(new ColumnExpression(aColumn));
		}

		/**
		 * Column.
		 *
		 * @param anColumn the an column
		 * @return the i expression operater stage
		 */
		@Override
		public IExpressionOperaterStage column(String anColumn) {
			return new ExpressionOperaterStage(new ColumnExpression(IColumn
				.builder()
				.name(anColumn)
				.build()));
		}

		/**
		 * Function.
		 *
		 * @param aFunction the a function
		 * @return the i expression operater stage
		 */
		@Override
		public IExpressionOperaterStage function(final ISqlFunction aFunction) {
			return new ExpressionOperaterStage(new ColumnExpression(aFunction));
		}
	}

	/**
	 * The Class ExpressionOperaterStage.
	 */
	private static final class ExpressionOperaterStage implements IExpressionOperaterStage {

		/** The first. */
		private final IExpression first;

		/**
		 * Instantiates a new expression operater stage.
		 *
		 * @param aFirst the a first
		 */
		private ExpressionOperaterStage(final IExpression aFirst) {
			this.first = aFirst;
		}

		/**
		 * Eq.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage eq() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.eq);
		}

		/**
		 * Neq.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage neq() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.neq);
		}

		/**
		 * Gt.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage gt() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.gt);
		}

		/**
		 * Gte.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage gte() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.gte);
		}

		/**
		 * Lt.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage lt() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.lt);
		}

		/**
		 * Lte.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage lte() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.lte);
		}

		/**
		 * In.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage in() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.in);
		}

		/**
		 * Any.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage any() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.any);
		}

		/**
		 * Like.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage like() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.like);
		}

		/**
		 * Ilike.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage ilike() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.ilike);
		}

		/**
		 * Fts.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage fts() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.fts);
		}

		/**
		 * Custom operator.
		 *
		 * @param anCustomOperator the an custom operator
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage customOperator(final IOperator anCustomOperator) {
			return new ExpressionBuilderFinalStage(this.first, anCustomOperator);
		}

		/**
		 * All.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage all() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.all);
		}

		/**
		 * Some.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage some() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.some);
		}

		/**
		 * Exists.
		 *
		 * @return the i expression final stage
		 */
		@Override
		public IExpressionFinalStage exists() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.exists);
		}

	}

	/**
	 * The Class ExpressionBuilderFinalStage.
	 */
	private static final class ExpressionBuilderFinalStage implements IExpressionFinalStage, IBuilder<IExpression> {

		/** The first. */
		private final IExpression first;

		/** The operator. */
		private final IOperator operator;

		/** The second. */
		private IExpression second;

		/**
		 * Instantiates a new expression builder final stage.
		 *
		 * @param aFirst    the a first
		 * @param aOperator the a operator
		 */
		public ExpressionBuilderFinalStage(final IExpression aFirst, final IOperator aOperator) {
			this.first = aFirst;
			this.operator = aOperator;
		}

		/**
		 * Value.
		 *
		 * @param aValue the a value
		 * @return the i builder
		 */
		@Override
		public IBuilder<IExpression> value(final Object aValue) {
			this.second = new StaticValueExpression(aValue);
			return this;
		}

		/**
		 * Values.
		 *
		 * @param aValues the a values
		 * @return the i builder
		 */
		@Override
		public IBuilder<IExpression> values(final Collection<?> aValues) {
			this.second = new StaticValueExpression(aValues);
			return this;
		}

		/**
		 * Values.
		 *
		 * @param aValues the a values
		 * @return the i builder
		 */
		@Override
		public IBuilder<IExpression> values(final Object[] aValues) {
			this.second = new StaticValueExpression(aValues);
			return this;
		}

		/**
		 * Variable.
		 *
		 * @param aName the a name
		 * @return the i builder
		 */
		@Override
		public IBuilder<IExpression> variable(final String aName) {
			this.second = new VariableExpression(aName);
			return this;
		}

		/**
		 * Column.
		 *
		 * @param aColumn the a column
		 * @return the i builder
		 */
		@Override
		public IBuilder<IExpression> column(final IColumn aColumn) {
			this.second = new ColumnExpression(aColumn);
			return this;
		}

		/**
		 * Function.
		 *
		 * @param aFunction the a function
		 * @return the i builder
		 */
		@Override
		public IBuilder<IExpression> function(final ISqlFunction aFunction) {
			this.second = new ColumnExpression(aFunction);
			return this;
		}

		/**
		 * Sub select.
		 *
		 * @param aSubSelect the a sub select
		 * @return the i builder
		 */
		@Override
		public IBuilder<IExpression> subSelect(final ISubSelect aSubSelect) {
			this.second = new SubselectExpression(aSubSelect);
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the i expression
		 */
		@Override
		public IExpression build() {
			return new Expression(this.first, this.operator, this.second);
		}
	}

	/**
	 * The Class SubselectExpression.
	 */
	private static class SubselectExpression implements IExpression {

		/** The select. */
		private ISubSelect select;

		/** The native sql. */
		private String nativeSql = null;

		/**
		 * Instantiates a new subselect expression.
		 *
		 * @param aSelect the a select
		 */
		public SubselectExpression(final ISubSelect aSelect) {
			this.select = aSelect;
		}

		/**
		 * Builds the native sql.
		 *
		 * @param anContext the an context
		 * @return the string
		 */
		@Override
		public String buildNativeSql(final INativeSqlBuildContext anContext) {
			if (this.nativeSql == null) {
				this.nativeSql = String.format("( %s )", this.select.buildNativeSql(anContext));
				this.select = null;
			}

			return this.nativeSql;
		}

	}

	/**
	 * The Class StaticValueExpression.
	 */
	public static class StaticValueExpression implements IExpression {

		/** The native sql. */
		private final String nativeSql;

		/**
		 * Instantiates a new static value expression.
		 *
		 * @param aValue the a value
		 */
		public StaticValueExpression(final Object aValue) {
			this.nativeSql = SqlHelper.toNativeSqlValue(aValue);
		}

		/**
		 * Builds the native sql.
		 *
		 * @param anContext the an context
		 * @return the string
		 */
		@Override
		public String buildNativeSql(final INativeSqlBuildContext anContext) {
			return this.nativeSql;
		}

	}

	/**
	 * The Class VariableExpression.
	 */
	public static class VariableExpression implements IExpression {

		/** The native sql. */
		private final String nativeSql;

		/**
		 * Instantiates a new variable expression.
		 *
		 * @param aName the a name
		 */
		public VariableExpression(final String aName) {
			this.nativeSql = String.format(":%s", aName);
		}

		/**
		 * Builds the native sql.
		 *
		 * @param anContext the an context
		 * @return the string
		 */
		@Override
		public String buildNativeSql(final INativeSqlBuildContext anContext) {
			return this.nativeSql;
		}
	}

	/**
	 * The Class ColumnExpression.
	 */
	public static class ColumnExpression implements IExpression {

		/** The column. */
		private final IColumn column;

		/**
		 * Instantiates a new column expression.
		 *
		 * @param anColumn the an column
		 */
		public ColumnExpression(final IColumn anColumn) {
			this.column = anColumn;
		}

		/**
		 * Builds the native sql.
		 *
		 * @param anContext the an context
		 * @return the string
		 */
		@Override
		public String buildNativeSql(final INativeSqlBuildContext anContext) {
			return this.column.buildNativeSql(anContext);
		}
	}

}
