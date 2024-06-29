package titus.jpa.nativequerybuilder.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import titus.jpa.nativequerybuilder.IBuilder;
import titus.jpa.nativequerybuilder.ITable;

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
	 * @param aName
	 *            the a name
	 * @param anAlias
	 *            the an alias
	 */
	private Table(final String aName, final String anAlias) {
		if (Utils.isNullOrBlank(aName))
			throw new RuntimeException("Name is required!");

		this.name = aName;
		this.alias = Utils.isNullOrBlank(anAlias) ? null : anAlias;
	}

	/**
	 * To native sql.
	 *
	 * @param anContext
	 *            the an context
	 * @return the string
	 * @see titus.jpa.nativequerybuilder.INativeSql#buildNativeSql(titus.jpa.nativequerybuilder.INativeSql.INativeSqlBuildContext)
	 */
	@Override
	public String buildNativeSql(final INativeSqlBuildContext anContext) {
		if (this.nativeSql == null) {
			StringBuilder builder = new StringBuilder();
			builder.append(this.name);
			if (Utils.isNotNullOrBlank(this.alias))
				builder.append(Constants.ONE_BLANK)
						.append(this.alias);

			this.nativeSql = builder.toString();
		}
		return this.nativeSql;
	}

	public static class TableBuilder implements ITableBuilder, ITableBuilderFinalStage, IBuilder<ITable> {

		private String	name;
		private String	alias;

		@Override
		public ITable build() {
			return new Table(this.name, this.alias);
		}

		@Override
		public ITableBuilderFinalStage name(final String aName) {
			this.name = aName;
			return this;
		}

		@Override
		public IBuilder<ITable> alias(final String anAlias) {
			this.alias = anAlias;
			return this;
		}

	}
}
