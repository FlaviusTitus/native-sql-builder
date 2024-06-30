package titus.sql.query.builder;

import titus.sql.query.builder.impl.Condition;

/**
 * The Interface ICondition.
 */
public interface ICondition extends INativeSql {

	/**
	 * Builder.
	 *
	 * @return the i condition builder
	 */
	public static IConditionBuilder builder() {
		return Condition.builder();
	}

	/**
	 * The Interface IConditionBuilder.
	 */
	public static interface IConditionBuilder {

		/**
		 * Not.
		 *
		 * @return the i condition builder
		 */
		IConditionBuilder not();

		/**
		 * Expression.
		 *
		 * @param anExpression the an expression
		 * @return the i condition builder logical stage
		 */
		IConditionBuilderLogicalStage expression(IExpression anExpression);

		/**
		 * Group.
		 *
		 * @param aConditionGroup the a condition group
		 * @return the i condition builder logical stage
		 */
		IConditionBuilderLogicalStage group(ICondition aConditionGroup);
	}

	/**
	 * The Interface IConditionBuilderExpressionStage.
	 */
	public static interface IConditionBuilderExpressionStage extends IConditionBuilder, IBuilder<ICondition> {

	}

	/**
	 * The Interface IConditionBuilderLogicalStage.
	 */
	public static interface IConditionBuilderLogicalStage extends IBuilder<ICondition> {

		/**
		 * And.
		 *
		 * @return the i condition builder expression stage
		 */
		IConditionBuilderExpressionStage and();

		/**
		 * Or.
		 *
		 * @return the i condition builder expression stage
		 */
		IConditionBuilderExpressionStage or();

	}

}
