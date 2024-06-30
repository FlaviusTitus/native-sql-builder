package titus.sql.query.builder.impl.functions;

import titus.sql.query.builder.IColumn;
import titus.sql.query.builder.functions.ICount;

/**
 * The Class Count.
 */
public class Count extends BasicSqlFunction implements ICount {

	/** The Constant NAME. */
	public static final String NAME = "count";

	/**
	 * Instantiates a new count.
	 *
	 * @param anColumn the an column
	 */
	private Count(final IColumn anColumn) {
		super(Count.NAME, anColumn);
	}

	/**
	 * Builder.
	 *
	 * @return the i basic sql function instance builder
	 */
	public static IBasicSqlFunctionInstanceBuilder<ICount> builder() {
		return new BasicSqlFunctionInstanceBuilder<>((column) -> new Count(column));
	}

}
