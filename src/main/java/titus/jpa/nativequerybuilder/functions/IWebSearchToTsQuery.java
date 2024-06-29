package titus.jpa.nativequerybuilder.functions;

import titus.jpa.nativequerybuilder.IBuilder;
import titus.jpa.nativequerybuilder.ISqlFunction;

public interface IWebSearchToTsQuery extends ISqlFunction {

	public static interface IWebSearchToTsQueryBuilder {
		IWebSearchToTsQueryBuilderValueStage defaultLanguage();

		IWebSearchToTsQueryBuilderValueStage language(String aLanguage);

	}

	public static interface IWebSearchToTsQueryBuilderValueStage extends IBuilder<IWebSearchToTsQuery> {
		IBuilder<IWebSearchToTsQuery> variable(String aVariableName);
	}
}
