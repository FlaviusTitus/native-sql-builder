package titus.jpa.nativequerybuilder.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import titus.jpa.nativequerybuilder.IBuilder;
import titus.jpa.nativequerybuilder.IColumn;
import titus.jpa.nativequerybuilder.ISelectable;
import titus.jpa.nativequerybuilder.ITable;
import titus.jpa.nativequerybuilder.ISelectable.ISelectableStageAliasBuilder;

/**
 * The Class Column.
 */
@Getter
@ToString
@EqualsAndHashCode
public class Column implements IColumn {

	/** The table. */
	private ITable table;

	/** The name. */
	private String name;

	/** The native sql. */
	private String nativeSql = null;

	/**
	 * Instantiates a new column.
	 *
	 * @param aTable
	 *            the a table
	 * @param aName
	 *            the a name
	 */
	private Column(final ITable aTable, final String aName) {
		this.table = aTable;
		this.name = aName;
	}

	/**
	 * Selectable.
	 *
	 * @return the i selectable stage alias builder
	 * @see titus.jpa.nativequerybuilder.IColumn#toSelectable()
	 */
	@Override
	public ISelectableStageAliasBuilder toSelectable() {
		return ISelectable.builder().column(this);
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
			if (this.table != null && Utils.isNotNullOrBlank(this.table.getAlias()))
				builder.append(this.table.getAlias()).append(SqlContants.NAMESPACE_DELIMITER);
			builder.append(this.name);
			this.nativeSql = builder.toString();
		}
		return this.nativeSql;
	}

	/**
	 * The Class ColumnBuilder.
	 */
	public static class ColumnBuilder implements IColumnBuilder, IColumnBuilderFromTableStage, IColumnBuilderSelectOrBuildStage {

		/** The table. */
		private ITable table;

		/** The name. */
		private String name;

		/**
		 * Table.
		 *
		 * @param aTable
		 *            the a table
		 * @return the i column builder name stage
		 * @see titus.jpa.nativequerybuilder.IColumn.IColumnBuilder#table(titus.jpa.nativequerybuilder.ITable)
		 */
		@Override
		public IColumnBuilderSelectOrBuildStage table(final ITable aTable) {
			this.table = aTable;
			return this;
		}

		/**
		 * Name.
		 *
		 * @param aName
		 *            the a name
		 * @return the i column builder from table stage
		 * @see titus.jpa.nativequerybuilder.IColumn.IColumnBuilder#name(java.lang.String)
		 */
		@Override
		public IColumnBuilderFromTableStage name(final String aName) {
			this.name = aName;
			return this;
		}

		/**
		 * Select.
		 *
		 * @return the i column builder select stage
		 * @see titus.jpa.nativequerybuilder.IColumn.IColumnBuilderSelectOrBuildStage#select()
		 */
		@Override
		public IColumnBuilderSelectStage select() {
			return new ColumnSelectBuilder(this.build());
		}

		/**
		 * Builds the.
		 *
		 * @return the i column
		 * @see titus.jpa.nativequerybuilder.IBuilder#build()
		 */
		@Override
		public IColumn build() {
			return new Column(this.table, this.name);
		}
	}

	/**
	 * The Class ColumnSelectBuilder.
	 */
	public static class ColumnSelectBuilder implements IColumnBuilderSelectStage, IBuilder<ISelectable> {

		/** The column. */
		private final IColumn column;

		/** The alias. */
		private String alias;

		/**
		 * Instantiates a new column select builder.
		 *
		 * @param anColumn
		 *            the an column
		 */
		public ColumnSelectBuilder(final IColumn anColumn) {
			super();
			this.column = anColumn;
		}

		/**
		 * As.
		 *
		 * @param anAlias
		 *            the an alias
		 * @return the i builder
		 * @see titus.jpa.nativequerybuilder.IColumn.IColumnBuilderSelectStage#as(java.lang.String)
		 */
		@Override
		public IBuilder<ISelectable> as(final String anAlias) {
			this.alias = anAlias;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the i selectable
		 * @see titus.jpa.nativequerybuilder.IBuilder#build()
		 */
		@Override
		public ISelectable build() {
			return ISelectable.builder().column(this.column).as(this.alias).build();
		}
	}

}
