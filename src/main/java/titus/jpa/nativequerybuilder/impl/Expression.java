package titus.jpa.nativequerybuilder.impl;

import java.util.Collection;

import titus.jpa.nativequerybuilder.IBuilder;
import titus.jpa.nativequerybuilder.IColumn;
import titus.jpa.nativequerybuilder.IExpression;
import titus.jpa.nativequerybuilder.IOperator;
import titus.jpa.nativequerybuilder.ISqlFunction;
import titus.jpa.nativequerybuilder.ISubSelect;

public class Expression implements IExpression {

	private IExpression first;
	private IOperator operator;
	private IExpression second;

	private String nativeSql;

	private Expression(final IExpression aFirst, final IOperator aOperator, final IExpression aSecond) {
		this.first = aFirst;
		this.operator = aOperator;
		this.second = aSecond;
	}

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

	public static IExpressionBuilder builder() {
		return new ExpressionBuilder();
	}

	public static final class ExpressionBuilder implements IExpressionBuilder {

		@Override
		public IExpressionOperaterStage column(final IColumn aColumn) {
			return new ExpressionOperaterStage(new ColumnExpression(aColumn));
		}

		@Override
		public IExpressionOperaterStage column(String anColumn) {
			return new ExpressionOperaterStage(new ColumnExpression(IColumn
				.builder()
				.name(anColumn)
				.build()));
		}

		@Override
		public IExpressionOperaterStage function(final ISqlFunction aFunction) {
			return new ExpressionOperaterStage(new ColumnExpression(aFunction));
		}
	}

	private static final class ExpressionOperaterStage implements IExpressionOperaterStage {

		private final IExpression first;

		private ExpressionOperaterStage(final IExpression aFirst) {
			this.first = aFirst;
		}

		@Override
		public IExpressionFinalStage eq() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.eq);
		}

		@Override
		public IExpressionFinalStage neq() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.neq);
		}

		@Override
		public IExpressionFinalStage gt() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.gt);
		}

		@Override
		public IExpressionFinalStage gte() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.gte);
		}

		@Override
		public IExpressionFinalStage lt() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.lt);
		}

		@Override
		public IExpressionFinalStage lte() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.lte);
		}

		@Override
		public IExpressionFinalStage in() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.in);
		}

		@Override
		public IExpressionFinalStage any() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.any);
		}

		@Override
		public IExpressionFinalStage like() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.like);
		}

		@Override
		public IExpressionFinalStage ilike() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.ilike);
		}

		@Override
		public IExpressionFinalStage fts() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.fts);
		}

		@Override
		public IExpressionFinalStage customOperator(final IOperator anCustomOperator) {
			return new ExpressionBuilderFinalStage(this.first, anCustomOperator);
		}

		@Override
		public IExpressionFinalStage all() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.all);
		}

		@Override
		public IExpressionFinalStage some() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.some);
		}

		@Override
		public IExpressionFinalStage exists() {
			return new ExpressionBuilderFinalStage(this.first, ExpressionOperator.exists);
		}

	}

	private static final class ExpressionBuilderFinalStage implements IExpressionFinalStage, IBuilder<IExpression> {

		private final IExpression first;
		private final IOperator operator;
		private IExpression second;

		public ExpressionBuilderFinalStage(final IExpression aFirst, final IOperator aOperator) {
			this.first = aFirst;
			this.operator = aOperator;
		}

		@Override
		public IBuilder<IExpression> value(final Object aValue) {
			this.second = new StaticValueExpression(aValue);
			return this;
		}

		@Override
		public IBuilder<IExpression> values(final Collection<?> aValues) {
			this.second = new StaticValueExpression(aValues);
			return this;
		}

		@Override
		public IBuilder<IExpression> values(final Object[] aValues) {
			this.second = new StaticValueExpression(aValues);
			return this;
		}

		@Override
		public IBuilder<IExpression> variable(final String aName) {
			this.second = new VariableExpression(aName);
			return this;
		}

		@Override
		public IBuilder<IExpression> column(final IColumn aColumn) {
			this.second = new ColumnExpression(aColumn);
			return this;
		}

		@Override
		public IBuilder<IExpression> function(final ISqlFunction aFunction) {
			this.second = new ColumnExpression(aFunction);
			return this;
		}

		@Override
		public IBuilder<IExpression> subSelect(final ISubSelect aSubSelect) {
			this.second = new SubselectExpression(aSubSelect);
			return this;
		}

		@Override
		public IExpression build() {
			return new Expression(this.first, this.operator, this.second);
		}
	}

	private static class SubselectExpression implements IExpression {

		private ISubSelect select;
		private String nativeSql = null;

		public SubselectExpression(final ISubSelect aSelect) {
			this.select = aSelect;
		}

		@Override
		public String buildNativeSql(final INativeSqlBuildContext anContext) {
			if (this.nativeSql == null) {
				this.nativeSql = String.format("( %s )", this.select.buildNativeSql(anContext));
				this.select = null;
			}

			return this.nativeSql;
		}

	}

	public static class StaticValueExpression implements IExpression {

		private final String nativeSql;

		public StaticValueExpression(final Object aValue) {
			this.nativeSql = SqlHelper.toNativeSqlValue(aValue);
		}

		@Override
		public String buildNativeSql(final INativeSqlBuildContext anContext) {
			return this.nativeSql;
		}

	}

	public static class VariableExpression implements IExpression {
		private final String nativeSql;

		public VariableExpression(final String aName) {
			this.nativeSql = String.format(":%s", aName);
		}

		@Override
		public String buildNativeSql(final INativeSqlBuildContext anContext) {
			return this.nativeSql;
		}
	}

	public static class ColumnExpression implements IExpression {
		private final IColumn column;

		public ColumnExpression(final IColumn anColumn) {
			this.column = anColumn;
		}

		@Override
		public String buildNativeSql(final INativeSqlBuildContext anContext) {
			return this.column.buildNativeSql(anContext);
		}
	}

}
