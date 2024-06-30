package titus.jpa.nativequerybuilder.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import titus.jpa.nativequerybuilder.IBuilder;
import titus.jpa.nativequerybuilder.IColumn;
import titus.jpa.nativequerybuilder.ISelectable;

/**
 * The Class SelectableColumn.
 */
@Getter
@ToString
@EqualsAndHashCode
public class Selectable implements ISelectable {

	/** The column. */
	private final IColumn column;

	/** The alias. */
	private final String alias;

	/** The native sql. */
	private String nativeSql = null;

	/**
	 * Instantiates a new selectable.
	 *
	 * @param column the column
	 * @param alias  the alias
	 */
	private Selectable(final IColumn column, final String alias) {
		super();
		this.column = column;
		this.alias = alias;
	}

	/**
	 * As column.
	 *
	 * @return the i column
	 */
	public IColumn asColumn() {
		return this.column;
	}

	/**
	 * Builds the native sql.
	 *
	 * @param anContext the an context
	 * @return the string
	 */
	@Override
	public String buildNativeSql(final INativeSqlBuildContext anContext) {
		if (this.nativeSql == null) {
			StringBuilder builder = new StringBuilder();
			builder.append(this.column.buildNativeSql(anContext));

			if (Utils.isNotNullOrBlank(this.alias)) {
				builder
					.append(Constants.ONE_BLANK)
					.append(SqlContants.KEYWORD__COLUMN_ALIAS)
					.append(Constants.ONE_BLANK)
					.append(this.alias);
			}

			this.nativeSql = builder.toString();
		}

		return this.nativeSql;
	}

	/**
	 * The Class SelectableBuilder.
	 */
	public static class SelectableBuilder
			implements ISelectableBuilder, ISelectableStageAliasBuilder, IBuilder<ISelectable> {

		/** The column. */
		private IColumn column;

		/** The alias. */
		private String alias;

		/**
		 * Builder.
		 *
		 * @return the i selectable builder
		 */
		public static ISelectableBuilder builder() {
			return new SelectableBuilder();
		}

		/**
		 * Builds the.
		 *
		 * @return the i selectable
		 * @see titus.jpa.nativequerybuilder.IBuilder#build()
		 */
		@Override
		public ISelectable build() {
			return new Selectable(this.column, this.alias);
		}

		/**
		 * As.
		 *
		 * @param anAlias the an alias
		 * @return the i builder
		 */
		@Override
		public IBuilder<ISelectable> as(final String anAlias) {
			this.alias = anAlias;
			return this;
		}

		/**
		 * Column.
		 *
		 * @param anColumn the an column
		 * @return the i selectable stage alias builder
		 * @see titus.jpa.nativequerybuilder.ISelectable.ISelectableBuilder#column(titus.jpa.nativequerybuilder.IColumn)
		 */
		@Override
		public ISelectableStageAliasBuilder column(final IColumn anColumn) {
			if (anColumn == null) {
				throw new NullPointerException("Column cannot be null!");
			}
			this.column = anColumn;
			return this;
		}

	}

}
