package titus.jpa.nativequerybuilder.impl.functions;

import titus.jpa.nativequerybuilder.IColumn;
import titus.jpa.nativequerybuilder.functions.ICount;

public class Count extends BasicSqlFunction implements ICount {

	public static final String NAME = "count";

	private Count(final IColumn anColumn) {
		super(Count.NAME, anColumn);
	}

	public static IBasicSqlFunctionInstanceBuilder<ICount> builder() {
		return new BasicSqlFunctionInstanceBuilder<>((column) -> new Count(column));
	}

}
