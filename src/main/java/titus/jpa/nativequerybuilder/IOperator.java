package titus.jpa.nativequerybuilder;

import titus.jpa.nativequerybuilder.INativeSql.INativeSqlBuildContext;

/**
 * The Interface IOperator.
 */
@FunctionalInterface
public interface IOperator {

	/**
	 * Builds the nativ sql.
	 *
	 * @param leftHand  the left hand
	 * @param rightHand the right hand
	 * @param aContext  the a context
	 * @return the string
	 */
	String buildNativSql(IExpression leftHand, IExpression rightHand, INativeSqlBuildContext aContext);

}
