package titus.jpa.nativequerybuilder.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import titus.jpa.nativequerybuilder.ICondition;
import titus.jpa.nativequerybuilder.IExpression;

@Getter
@ToString
@EqualsAndHashCode
public class Condition implements ICondition {

	private List<IExpression>	expressions;
	private String				nativeSql	= null;

	private Condition(final List<IExpression> theExpressions) {
		this.expressions = theExpressions;
	}

	@Override
	public String buildNativeSql(final INativeSqlBuildContext anContext) {
		if (this.nativeSql == null) {
			if (this.expressions == null || this.expressions.isEmpty())
				this.nativeSql = "";
			else
				this.nativeSql = this.expressions
						.stream()
						.map(expression -> expression.buildNativeSql(anContext))
						.collect(Collectors.joining(Constants.ONE_BLANK));

			this.expressions = null;
		}

		return this.nativeSql;
	}

	public static IConditionBuilder builder() {
		return new ConditionBuilder();
	}

	public static class ConditionBuilder implements IConditionBuilder, IConditionBuilderExpressionStage, IConditionBuilderLogicalStage {

		private final List<IExpression> expressions = new ArrayList<>();

		@Override
		public ICondition build() {
			return new Condition(this.expressions);
		}

		@Override
		public IConditionBuilderExpressionStage and() {
			this.expressions.add(ConditionCombinators.and);
			return this;
		}

		@Override
		public IConditionBuilderExpressionStage or() {
			this.expressions.add(ConditionCombinators.or);
			return this;
		}

		@Override
		public IConditionBuilder not() {
			this.expressions.add(ConditionCombinators.not);
			return this;
		}

		@Override
		public IConditionBuilderLogicalStage expression(final IExpression anExpression) {
			if (anExpression == null)
				throw new IllegalArgumentException("Expression cannot be null!");

			this.expressions.add(anExpression);
			return this;
		}

		@Override
		public IConditionBuilderLogicalStage group(final ICondition aConditionGroup) {
			if (aConditionGroup == null)
				throw new IllegalArgumentException("Condition group cannot be null!");

			this.expressions.add(new ConditionGroupExpression(aConditionGroup));
			return this;
		}
	}

	private static class ConditionGroupExpression implements IExpression {

		private ICondition condition;

		private String nativeSql = null;

		ConditionGroupExpression(final ICondition aCondition) {
			this.condition = aCondition;
		}

		@Override
		public String buildNativeSql(final INativeSqlBuildContext anContext) {
			if (this.nativeSql == null) {
				this.nativeSql = this.condition == null ? "" : String.format("( %s )", this.condition.buildNativeSql(anContext));
				this.condition = null;
			}

			return this.nativeSql;
		}
	}
}
