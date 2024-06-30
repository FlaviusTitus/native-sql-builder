package titus.jpa.nativequerybuilder.impl.functions;

import titus.jpa.nativequerybuilder.IColumn;
import titus.jpa.nativequerybuilder.functions.ICount;

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
