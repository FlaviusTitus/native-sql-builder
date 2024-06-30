package titus.sql.query.builder;

/**
 * The Interface INativeSql.
 */
public interface INativeSql {

	/**
	 * The Interface INativeSqlBuildContext.
	 */
	public static interface INativeSqlBuildContext {
	}

	/**
	 * Builds the native sql.
	 *
	 * @param anContext the an context
	 * @return the string
	 */
	String buildNativeSql(INativeSqlBuildContext anContext);
}
