package titus.sql.query.builder.impl.functions;

import titus.sql.query.builder.IBuilder;
import titus.sql.query.builder.functions.IWebSearchToTsQuery;
import titus.sql.query.builder.impl.Expression.VariableExpression;

/**
 * The Class FtsWebQuery.
 */
public class WebSearchToTsQuery implements IWebSearchToTsQuery {

	/** The Constant DEFAULT_LANGUAGE. */
	public static final String DEFAULT_LANGUAGE = "simple";

	/** The variable. */
	private final VariableExpression variable;

	/** The language. */
	private final String language;

	/**
	 * Instantiates a new web search to ts query.
	 *
	 * @param aLanguage the a language
	 * @param aVariable the a variable
	 */
	private WebSearchToTsQuery(final String aLanguage, final VariableExpression aVariable) {
		this.language = aLanguage;
		this.variable = aVariable;
	}

	/**
	 * Builds the native sql.
	 *
	 * @param anContext the an context
	 * @return the string
	 * @see titus.sql.query.builder.INativeSql#buildNativeSql(titus.sql.query.builder.INativeSql.INativeSqlBuildContext)
	 */
	@Override
	public String buildNativeSql(final INativeSqlBuildContext anContext) {
		return String
			.format("websearch_to_tsquery( '%s', %s )", this.language, this.variable.buildNativeSql(anContext));
	}

	/**
	 * Builder.
	 *
	 * @return the i fts web query builder
	 */
	public static IWebSearchToTsQueryBuilder builder() {
		return new FtsWebQueryBuilder();
	}

	/**
	 * The Class FtsWebQueryBuilder.
	 */
	public static class FtsWebQueryBuilder
			implements IWebSearchToTsQueryBuilder, IWebSearchToTsQueryBuilderValueStage, IBuilder<IWebSearchToTsQuery> {

		/** The language. */
		private String language;

		/** The valiable. */
		private VariableExpression valiable;

		/**
		 * Default language.
		 *
		 * @return the i web search to ts query builder value stage
		 * @see titus.sql.query.builder.functions.IWebSearchToTsQuery.IWebSearchToTsQueryBuilder#defaultLanguage()
		 */
		@Override
		public IWebSearchToTsQueryBuilderValueStage defaultLanguage() {
			this.language = WebSearchToTsQuery.DEFAULT_LANGUAGE;
			return this;
		}

		/**
		 * Language.
		 *
		 * @param aLanguage the a language
		 * @return the i fts web query builder column stage
		 * @see titus.sql.query.builder.functions.IWebSearchToTsQuery.IWebSearchToTsQueryBuilder#language(java.lang.String)
		 */
		@Override
		public IWebSearchToTsQueryBuilderValueStage language(final String aLanguage) {
			this.language = aLanguage;
			return this;
		}

		/**
		 * Variable.
		 *
		 * @param aVariableName the a variable name
		 * @return the i builder
		 * @see titus.sql.query.builder.functions.IWebSearchToTsQuery.IWebSearchToTsQueryBuilderValueStage#variable(java.lang.String)
		 */
		@Override
		public IBuilder<IWebSearchToTsQuery> variable(final String aVariableName) {
			this.valiable = new VariableExpression(aVariableName);
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the i fts web query
		 * @see titus.sql.query.builder.IBuilder#build()
		 */
		@Override
		public IWebSearchToTsQuery build() {
			return new WebSearchToTsQuery(this.language, this.valiable);
		}

	}

}
