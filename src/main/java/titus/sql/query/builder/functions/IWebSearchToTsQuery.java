package titus.sql.query.builder.functions;

import titus.sql.query.builder.IBuilder;
import titus.sql.query.builder.ISqlFunction;

/**
 * The Interface IWebSearchToTsQuery.
 */
public interface IWebSearchToTsQuery extends ISqlFunction {

	/**
	 * The Interface IWebSearchToTsQueryBuilder.
	 */
	public static interface IWebSearchToTsQueryBuilder {

		/**
		 * Default language.
		 *
		 * @return the i web search to ts query builder value stage
		 */
		IWebSearchToTsQueryBuilderValueStage defaultLanguage();

		/**
		 * Language.
		 *
		 * @param aLanguage the a language
		 * @return the i web search to ts query builder value stage
		 */
		IWebSearchToTsQueryBuilderValueStage language(String aLanguage);

	}

	/**
	 * The Interface IWebSearchToTsQueryBuilderValueStage.
	 */
	public static interface IWebSearchToTsQueryBuilderValueStage extends IBuilder<IWebSearchToTsQuery> {

		/**
		 * Variable.
		 *
		 * @param aVariableName the a variable name
		 * @return the i builder
		 */
		IBuilder<IWebSearchToTsQuery> variable(String aVariableName);
	}
}
