package titus.jpa.nativequerybuilder.impl.functions;

import java.util.function.Function;

import titus.jpa.nativequerybuilder.IBuilder;
import titus.jpa.nativequerybuilder.IColumn;
import titus.jpa.nativequerybuilder.ISqlFunction;
import titus.jpa.nativequerybuilder.ISqlFunction.IBasicSqlFunctionInstanceBuilder;

/**
 * The Class CountBuilder.
 */
public class BasicSqlFunctionInstanceBuilder<R extends ISqlFunction> implements IBasicSqlFunctionInstanceBuilder<R>, IBuilder<R> {

	/** The function instance builder. */
	private final Function<IColumn, R> functionInstanceBuilder;

	/** The column. */
	private IColumn column;

	/**
	 * @param aFunctionInstanceBuilder
	 */
	public BasicSqlFunctionInstanceBuilder(final Function<IColumn, R> aFunctionInstanceBuilder) {
		this.functionInstanceBuilder = aFunctionInstanceBuilder;
	}

	/**
	 * Column.
	 *
	 * @param anColumn
	 *            the an column
	 * @return the i builder
	 * @see titus.utils.jpa.nativequerybuilder.functions.ICount.ICountBuilder#column(titus.jpa.nativequerybuilder.IColumn)
	 */
	@Override
	public IBuilder<R> column(final IColumn anColumn) {
		this.column = anColumn;
		return this;
	}

	/**
	 * Column.
	 *
	 * @param anColumn
	 *            the an column
	 * @return the i builder
	 * @see titus.utils.jpa.nativequerybuilder.functions.ICount.ICountBuilder#column(java.lang.String)
	 */
	@Override
	public IBuilder<R> column(final String anColumn) {
		this.column = IColumn.builder().name(anColumn).build();
		return this;
	}

	/**
	 * Builds the.
	 *
	 * @return the r
	 * @see titus.jpa.nativequerybuilder.IBuilder#build()
	 */
	@Override
	public R build() {
		return this.functionInstanceBuilder.apply(this.column);
	}

}