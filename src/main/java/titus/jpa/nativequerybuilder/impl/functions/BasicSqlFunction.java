package titus.jpa.nativequerybuilder.impl.functions;

import titus.jpa.nativequerybuilder.IColumn;
import titus.jpa.nativequerybuilder.ISqlFunction;

public class BasicSqlFunction implements ISqlFunction {

	/** The column. */
	private final IColumn column;

	/** The function name. */
	private final String functionName;

	/**
	 * Instantiates a new count.
	 *
	 * @param anColumn
	 *            the an column
	 */
	public BasicSqlFunction(final String aFunctionName, final IColumn anColumn) {
		this.functionName = aFunctionName;
		this.column = anColumn;
	}

	/**
	 * Builds the native sql.
	 *
	 * @param anContext
	 *            the an context
	 * @return the string
	 * @see titus.jpa.nativequerybuilder.INativeSql#buildNativeSql(titus.jpa.nativequerybuilder.INativeSql.INativeSqlBuildContext)
	 */
	@Override
	public String buildNativeSql(final INativeSqlBuildContext anContext) {
		return String.format("%s( %s )", this.functionName, this.column.buildNativeSql(anContext));
	}
}
