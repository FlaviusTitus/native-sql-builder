package titus.sql.query.builder;

/**
 * The Interface IBuilder.
 *
 * @param <R> the generic type
 */
public interface IBuilder<R> {

	/**
	 * Builds the.
	 *
	 * @return the r
	 */
	public R build();
}
