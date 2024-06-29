package titus.jpa.nativequerybuilder;

import titus.jpa.nativequerybuilder.impl.Condition;

public interface ICondition extends INativeSql {

	public static IConditionBuilder builder() {
		return Condition.builder();
	}

	public static interface IConditionBuilder {

		IConditionBuilder not();

		IConditionBuilderLogicalStage expression(IExpression anExpression);

		IConditionBuilderLogicalStage group(ICondition aConditionGroup);
	}

	public static interface IConditionBuilderExpressionStage extends IConditionBuilder, IBuilder<ICondition> {

	}

	public static interface IConditionBuilderLogicalStage extends IBuilder<ICondition> {

		IConditionBuilderExpressionStage and();

		IConditionBuilderExpressionStage or();

	}

}
