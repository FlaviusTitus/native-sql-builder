package titus.sql.query.builder.impl;

import titus.sql.query.builder.IExpression;
import titus.sql.query.builder.IOperator;
import titus.sql.query.builder.INativeSql.INativeSqlBuildContext;

/**
 * The Enum ExpressionOperator.
 */
public enum ExpressionOperator implements IOperator {

	/** The eq. */
	eq((left, right, context) -> String.format("%s = %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The neq. */
	neq((left, right, context) -> String
		.format("%s <> %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The gt. */
	gt((left, right, context) -> String.format("%s > %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The gte. */
	gte((left, right, context) -> String
		.format("%s >= %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The lt. */
	lt((left, right, context) -> String.format("%s < %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The lte. */
	lte((left, right, context) -> String
		.format("%s <= %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The like. */
	like((left, right, context) -> String
		.format("%s like %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The ilike. */
	ilike((left, right, context) -> String
		.format("%s ilike %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The any. */
	any((left, right, context) -> String
		.format("%s any %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The fts. */
	fts((left, right, context) -> String
		.format("%s @@ %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The in. */
	in((left, right, context) -> String
		.format("%s in %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The exists. */
	exists((left, right, context) -> String
		.format("%s exists %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The some. */
	some((left, right, context) -> String
		.format("%s some %s", left.buildNativeSql(context), right.buildNativeSql(context))),
	/** The all. */
	all((left, right, context) -> String
		.format("%s all %s", left.buildNativeSql(context), right.buildNativeSql(context))),
		;

	/** The operator. */
	private final IOperator operator;

	/**
	 * Instantiates a new expression operator.
	 *
	 * @param aOperator the a operator
	 */
	ExpressionOperator(final IOperator aOperator) {
		this.operator = aOperator;
	}

	/**
	 * Builds the nativ sql.
	 *
	 * @param leftHand  the left hand
	 * @param rightHand the right hand
	 * @param aContext  the a context
	 * @return the string
	 */
	@Override
	public String buildNativSql(final IExpression leftHand, final IExpression rightHand,
			final INativeSqlBuildContext aContext) {
		return this.operator.buildNativSql(leftHand, rightHand, aContext);
	}
}
