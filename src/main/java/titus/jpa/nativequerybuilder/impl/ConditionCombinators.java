package titus.jpa.nativequerybuilder.impl;

import titus.jpa.nativequerybuilder.IExpression;

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
	 * @see titus.jpa.nativequerybuilder.INativeSql#buildNativeSql(titus.jpa.nativequerybuilder.INativeSql.INativeSqlBuildContext)
	 */
	@Override
	public String buildNativeSql(final INativeSqlBuildContext anContext) {
		return this.combinator;
	}
}
