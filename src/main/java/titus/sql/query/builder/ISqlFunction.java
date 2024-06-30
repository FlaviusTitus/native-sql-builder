package titus.sql.query.builder;

import titus.sql.query.builder.functions.ICount;
import titus.sql.query.builder.functions.IWebSearchToTsQuery.IWebSearchToTsQueryBuilder;
import titus.sql.query.builder.impl.SqlFunctionBuilder;

/**
 * The Interface ISqlFunction.
 */
public interface ISqlFunction extends IColumn {

	/**
	 * To column.
	 *
	 * @return the i column
	 */
	default IColumn toColumn() {
		return this;
	}

	/**
	 * Builder.
	 *
	 * @return the i sql function builder
	 */
	public static ISqlFunctionBuilder builder() {
		return SqlFunctionBuilder.builder();
	}

	/**
	 * The Interface IBasicSqlFunctionInstanceBuilder.
	 *
	 * @param <R> the generic type
	 */
	public static interface IBasicSqlFunctionInstanceBuilder<R extends ISqlFunction> {

		/**
		 * Column.
		 *
		 * @param anColumn the an column
		 * @return the i builder
		 */
		IBuilder<R> column(IColumn anColumn);

		/**
		 * Column.
		 *
		 * @param anColumn the an column
		 * @return the i builder
		 */
		IBuilder<R> column(String anColumn);
	}

	/**
	 * The Interface ISqlFunctionBuilder.
	 */
	public static interface ISqlFunctionBuilder {

		/**
		 * Count.
		 *
		 * @return the i basic sql function instance builder
		 */
		IBasicSqlFunctionInstanceBuilder<ICount> count();

		/**
		 * Fts web query.
		 *
		 * @return the i web search to ts query builder
		 */
		IWebSearchToTsQueryBuilder ftsWebQuery();

	}
}
