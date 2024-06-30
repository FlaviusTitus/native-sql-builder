package titus.sql.query.builder.impl;

import titus.sql.query.builder.IExpression;

/**
 * The Enum LogicalCombinators.
 */
public enum ConditionCombinators implements IExpression {

	/** The and. */
	and(SqlContants.KEYWORD__AND),
	/** The or. */
	or(SqlContants.KEYWORD__OR),
	/** The not. */
	not(SqlContants.KEYWORD__NOT)

	;

	/** The combinator. */
	private final String combinator;

	/**
	 * Instantiates a new logical combinators.
	 *
	 * @param aCombinator
	 *            the a combinator
	 */
	private ConditionCombinators(final String aCombinator) {
		this.combinator = aCombinator;
	}

	/**
	 * To native sql.
	 *
	 * @param anContext
	 *            the an context
	 * @return the string
	 * @see titus.sql.query.builder.INativeSql#buildNativeSql(titus.sql.query.builder.INativeSql.INativeSqlBuildContext)
	 */
	@Override
	public String buildNativeSql(final INativeSqlBuildContext anContext) {
		return this.combinator;
	}
}
