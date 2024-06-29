package titus.jpa.nativequerybuilder.impl;

import titus.jpa.nativequerybuilder.ISqlFunction.IBasicSqlFunctionInstanceBuilder;
import titus.jpa.nativequerybuilder.ISqlFunction.ISqlFunctionBuilder;
import titus.jpa.nativequerybuilder.functions.ICount;
import titus.jpa.nativequerybuilder.functions.IWebSearchToTsQuery.IWebSearchToTsQueryBuilder;
import titus.jpa.nativequerybuilder.impl.functions.Count;
import titus.jpa.nativequerybuilder.impl.functions.WebSearchToTsQuery;

public class SqlFunctionBuilder implements ISqlFunctionBuilder {

	private SqlFunctionBuilder() {
	}

	public static ISqlFunctionBuilder builder() {
		return new SqlFunctionBuilder();
	}

	@Override
	public IBasicSqlFunctionInstanceBuilder<ICount> count() {
		return Count.builder();
	}

	@Override
	public IWebSearchToTsQueryBuilder ftsWebQuery() {
		return WebSearchToTsQuery.builder();
	}
}
