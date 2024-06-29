package titus.jpa.nativequerybuilder.impl;

public final class SqlContants {

	private SqlContants() {
	}

	public static final String NAMESPACE_DELIMITER = ".";

	public static final String VALUE_DELIMITER = ", ";

	public static final String STRING_ESCAPE_CHAR = "'";

	public static final String COUNT_ROWS = "count( * )";

	public static final Object COUNT_ROWS_DISTINCT = "count( distinct * )";

	public static final String SELECT_ALL_COLUMNS = "*";

	public static final String KEYWORD__COLUMN_ALIAS = "AS";

	public static final String KEYWORD__SELECT = "SELECT";

	public static final String KEYWORD__DISTINCT = "DISTINCT";

	public static final String KEYWORD__FROM = "FROM";

	public static final String KEYWORD__WHERE = "WHERE";

	public static final String KEYWORD__AND = "AND";

	public static final String KEYWORD__OR = "OR";

	public static final String KEYWORD__NOT = "NOT";

	public static final String KEYWORD_NULL = "NULL";

}
