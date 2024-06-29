package titus.jpa.nativequerybuilder;

import titus.jpa.nativequerybuilder.impl.SelectBuilder;

/**
 * The Interface ISelectStatement.
 */
public interface ISelect {

	/**
	 * Gets the data query.
	 *
	 * @return the data query
	 */
	String getDataQuery();

	/**
	 * Gets the count query.
	 *
	 * @return the count query
	 */
	String getCountQuery();

	/**
	 * Builder.
	 *
	 * @return the i select builder
	 */
	public static ISelectBuilder builder() {
		return SelectBuilder.builder();
	}
}
