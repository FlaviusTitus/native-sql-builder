package titus.sql.query.builder.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import titus.sql.query.builder.IBuilder;
import titus.sql.query.builder.ITable;

/**
 * The Class Table.
 */
@Getter
@ToString
@EqualsAndHashCode
public class Table implements ITable {

	/** The name. */
	private final String name;

	/** The alias. */
	private final String alias;

	/** The native sql. */
	private String nativeSql;

	/**
	 * Instantiates a new table.
	 *
	 * @param aName   the a name
	 * @param anAlias the an alias
	 */
	private Table(final String aName, final String anAlias) {
		if (Utils.isNullOrBlank(aName)) {
			throw new RuntimeException("Name is required!");
		}

		this.name = aName;
		this.alias = Utils.isNullOrBlank(anAlias) ? null : anAlias;
	}

	/**
	 * To native sql.
	 *
	 * @param anContext the an context
	 * @return the string
	 * @see titus.sql.query.builder.INativeSql#buildNativeSql(titus.sql.query.builder.INativeSql.INativeSqlBuildContext)
	 */
	@Override
	public String buildNativeSql(final INativeSqlBuildContext anContext) {
		if (this.nativeSql == null) {
			StringBuilder builder = new StringBuilder();
			builder.append(this.name);
			if (Utils.isNotNullOrBlank(this.alias)) {
				builder
					.append(Constants.ONE_BLANK)
					.append(this.alias);
			}

			this.nativeSql = builder.toString();
		}
		return this.nativeSql;
	}

	/**
	 * The Class TableBuilder.
	 */
	public static class TableBuilder implements ITableBuilder, ITableBuilderFinalStage, IBuilder<ITable> {

		/** The name. */
		private String name;

		/** The alias. */
		private String alias;

		/**
		 * Builds the.
		 *
		 * @return the i table
		 */
		@Override
		public ITable build() {
			return new Table(this.name, this.alias);
		}

		/**
		 * Name.
		 *
		 * @param aName the a name
		 * @return the i table builder final stage
		 */
		@Override
		public ITableBuilderFinalStage name(final String aName) {
			this.name = aName;
			return this;
		}

		/**
		 * Alias.
		 *
		 * @param anAlias the an alias
		 * @return the i builder
		 */
		@Override
		public IBuilder<ITable> alias(final String anAlias) {
			this.alias = anAlias;
			return this;
		}

	}
}
