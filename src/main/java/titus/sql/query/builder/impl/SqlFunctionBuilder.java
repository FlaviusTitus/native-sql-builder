package titus.sql.query.builder.impl;

import titus.sql.query.builder.ISqlFunction.IBasicSqlFunctionInstanceBuilder;
import titus.sql.query.builder.ISqlFunction.ISqlFunctionBuilder;
import titus.sql.query.builder.functions.ICount;
import titus.sql.query.builder.functions.IWebSearchToTsQuery.IWebSearchToTsQueryBuilder;
import titus.sql.query.builder.impl.functions.Count;
import titus.sql.query.builder.impl.functions.WebSearchToTsQuery;

/**
 * The Class SqlFunctionBuilder.
 */
public class SqlFunctionBuilder implements ISqlFunctionBuilder {

	/**
	 * Instantiates a new sql function builder.
	 */
	private SqlFunctionBuilder() {
	}

	/**
	 * Builder.
	 *
	 * @return the i sql function builder
	 */
	public static ISqlFunctionBuilder builder() {
		return new SqlFunctionBuilder();
	}

	/**
	 * Count.
	 *
	 * @return the i basic sql function instance builder
	 */
	@Override
	public IBasicSqlFunctionInstanceBuilder<ICount> count() {
		return Count.builder();
	}

	/**
	 * Fts web query.
	 *
	 * @return the i web search to ts query builder
	 */
	@Override
	public IWebSearchToTsQueryBuilder ftsWebQuery() {
		return WebSearchToTsQuery.builder();
	}
}
