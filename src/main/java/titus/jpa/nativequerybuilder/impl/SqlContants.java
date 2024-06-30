package titus.jpa.nativequerybuilder.impl;

/**
 * The Class SqlContants.
 */
public final class SqlContants {

	/**
	 * Instantiates a new sql contants.
	 */
	private SqlContants() {
	}

	/** The Constant NAMESPACE_DELIMITER. */
	public static final String NAMESPACE_DELIMITER = ".";

	/** The Constant VALUE_DELIMITER. */
	public static final String VALUE_DELIMITER = ", ";

	/** The Constant STRING_ESCAPE_CHAR. */
	public static final String STRING_ESCAPE_CHAR = "'";

	/** The Constant COUNT_ROWS. */
	public static final String COUNT_ROWS = "count( * )";

	/** The Constant COUNT_ROWS_DISTINCT. */
	public static final Object COUNT_ROWS_DISTINCT = "count( distinct * )";

	/** The Constant SELECT_ALL_COLUMNS. */
	public static final String SELECT_ALL_COLUMNS = "*";

	/** The Constant KEYWORD__COLUMN_ALIAS. */
	public static final String KEYWORD__COLUMN_ALIAS = "AS";

	/** The Constant KEYWORD__SELECT. */
	public static final String KEYWORD__SELECT = "SELECT";

	/** The Constant KEYWORD__DISTINCT. */
	public static final String KEYWORD__DISTINCT = "DISTINCT";

	/** The Constant KEYWORD__FROM. */
	public static final String KEYWORD__FROM = "FROM";

	/** The Constant KEYWORD__WHERE. */
	public static final String KEYWORD__WHERE = "WHERE";

	/** The Constant KEYWORD__AND. */
	public static final String KEYWORD__AND = "AND";

	/** The Constant KEYWORD__OR. */
	public static final String KEYWORD__OR = "OR";

	/** The Constant KEYWORD__NOT. */
	public static final String KEYWORD__NOT = "NOT";

	/** The Constant KEYWORD_NULL. */
	public static final String KEYWORD_NULL = "NULL";

}
