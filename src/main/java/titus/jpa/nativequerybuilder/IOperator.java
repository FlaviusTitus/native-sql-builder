package titus.jpa.nativequerybuilder;

import titus.jpa.nativequerybuilder.INativeSql.INativeSqlBuildContext;

@FunctionalInterface
public interface IOperator {

	String buildNativSql(IExpression leftHand, IExpression rightHand, INativeSqlBuildContext aContext);

}