package titus.sql.query.builder.impl.functions;

import java.util.function.Function;

import titus.sql.query.builder.IBuilder;
import titus.sql.query.builder.IColumn;
import titus.sql.query.builder.ISqlFunction;
import titus.sql.query.builder.ISqlFunction.IBasicSqlFunctionInstanceBuilder;

/**
 * The Class CountBuilder.
 *
 * @param <R> the generic type
 */
public class BasicSqlFunctionInstanceBuilder<R extends ISqlFunction>
		implements IBasicSqlFunctionInstanceBuilder<R>, IBuilder<R> {

	/** The function instance builder. */
	private final Function<IColumn, R> functionInstanceBuilder;

	/** The column. */
	private IColumn column;

	/**
	 * Instantiates a new basic sql function instance builder.
	 *
	 * @param aFunctionInstanceBuilder the a function instance builder
	 */
	public BasicSqlFunctionInstanceBuilder(final Function<IColumn, R> aFunctionInstanceBuilder) {
		this.functionInstanceBuilder = aFunctionInstanceBuilder;
	}

	/**
	 * Column.
	 *
	 * @param anColumn the an column
	 * @return the i builder
	 */
	@Override
	public IBuilder<R> column(final IColumn anColumn) {
		this.column = anColumn;
		return this;
	}

	/**
	 * Column.
	 *
	 * @param anColumn the an column
	 * @return the i builder
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
	 */
	@Override
	public R build() {
		return this.functionInstanceBuilder.apply(this.column);
	}

}
