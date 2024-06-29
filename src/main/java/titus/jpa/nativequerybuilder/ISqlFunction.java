package titus.jpa.nativequerybuilder;

import titus.jpa.nativequerybuilder.functions.ICount;
import titus.jpa.nativequerybuilder.functions.IWebSearchToTsQuery.IWebSearchToTsQueryBuilder;
import titus.jpa.nativequerybuilder.impl.SqlFunctionBuilder;

public interface ISqlFunction extends IColumn {

	default IColumn toColumn() {
		return this;
	}

	public static ISqlFunctionBuilder builder() {
		return SqlFunctionBuilder.builder();
	}

	public static interface IBasicSqlFunctionInstanceBuilder<R extends ISqlFunction> {
		IBuilder<R> column(IColumn anColumn);

		IBuilder<R> column(String anColumn);
	}

	public static interface ISqlFunctionBuilder {
		IBasicSqlFunctionInstanceBuilder<ICount> count();

		IWebSearchToTsQueryBuilder ftsWebQuery();

	}
}
