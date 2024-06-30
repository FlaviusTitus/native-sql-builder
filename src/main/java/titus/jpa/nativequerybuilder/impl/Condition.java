package titus.jpa.nativequerybuilder.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import titus.jpa.nativequerybuilder.ICondition;
import titus.jpa.nativequerybuilder.IExpression;

/**
 * The Class Condition.
 */
@Getter
@ToString
@EqualsAndHashCode
public class Condition implements ICondition {

	/** The expressions. */
	private List<IExpression> expressions;

	/** The native sql. */
	private String nativeSql = null;

	/**
	 * Instantiates a new condition.
	 *
	 * @param theExpressions the the expressions
	 */
	private Condition(final List<IExpression> theExpressions) {
		this.expressions = theExpressions;
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
			if (this.expressions == null || this.expressions.isEmpty()) {
				this.nativeSql = "";
			} else {
				this.nativeSql = this.expressions
					.stream()
					.map(expression -> expression.buildNativeSql(anContext))
					.collect(Collectors.joining(Constants.ONE_BLANK));
			}

			this.expressions = null;
		}

		return this.nativeSql;
	}

	/**
	 * Builder.
	 *
	 * @return the i condition builder
	 */
	public static IConditionBuilder builder() {
		return new ConditionBuilder();
	}

	/**
	 * The Class ConditionBuilder.
	 */
	public static class ConditionBuilder
			implements IConditionBuilder, IConditionBuilderExpressionStage, IConditionBuilderLogicalStage {

		/** The expressions. */
		private final List<IExpression> expressions = new ArrayList<>();

		/**
		 * Builds the.
		 *
		 * @return the i condition
		 */
		@Override
		public ICondition build() {
			return new Condition(this.expressions);
		}

		/**
		 * And.
		 *
		 * @return the i condition builder expression stage
		 */
		@Override
		public IConditionBuilderExpressionStage and() {
			this.expressions.add(ConditionCombinators.and);
			return this;
		}

		/**
		 * Or.
		 *
		 * @return the i condition builder expression stage
		 */
		@Override
		public IConditionBuilderExpressionStage or() {
			this.expressions.add(ConditionCombinators.or);
			return this;
		}

		/**
		 * Not.
		 *
		 * @return the i condition builder
		 */
		@Override
		public IConditionBuilder not() {
			this.expressions.add(ConditionCombinators.not);
			return this;
		}

		/**
		 * Expression.
		 *
		 * @param anExpression the an expression
		 * @return the i condition builder logical stage
		 */
		@Override
		public IConditionBuilderLogicalStage expression(final IExpression anExpression) {
			if (anExpression == null) {
				throw new IllegalArgumentException("Expression cannot be null!");
			}

			this.expressions.add(anExpression);
			return this;
		}

		/**
		 * Group.
		 *
		 * @param aConditionGroup the a condition group
		 * @return the i condition builder logical stage
		 */
		@Override
		public IConditionBuilderLogicalStage group(final ICondition aConditionGroup) {
			if (aConditionGroup == null) {
				throw new IllegalArgumentException("Condition group cannot be null!");
			}

			this.expressions.add(new ConditionGroupExpression(aConditionGroup));
			return this;
		}
	}

	/**
	 * The Class ConditionGroupExpression.
	 */
	private static class ConditionGroupExpression implements IExpression {

		/** The condition. */
		private ICondition condition;

		/** The native sql. */
		private String nativeSql = null;

		/**
		 * Instantiates a new condition group expression.
		 *
		 * @param aCondition the a condition
		 */
		ConditionGroupExpression(final ICondition aCondition) {
			this.condition = aCondition;
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
				this.nativeSql = this.condition == null ? ""
						: String.format("( %s )", this.condition.buildNativeSql(anContext));
				this.condition = null;
			}

			return this.nativeSql;
		}
	}
}
