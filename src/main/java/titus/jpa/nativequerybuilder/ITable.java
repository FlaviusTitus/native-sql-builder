package titus.jpa.nativequerybuilder;

import titus.jpa.nativequerybuilder.impl.Table.TableBuilder;

/**
 * The Interface ITable.
 */
public interface ITable extends INativeSql, ISelectFrom {

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	String getAlias();

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Column.
	 *
	 * @param aName the a name
	 * @return the i column
	 */
	default IColumn column(final String aName) {
		return IColumn.builder().name(aName).table(this).build();
	}

	/**
	 * Select column.
	 *
	 * @param aName the a name
	 * @return the i selectable
	 */
	default ISelectable selectColumn(final String aName) {
		return IColumn.builder().name(aName).table(this).select().build();
	}

	/**
	 * Select column.
	 *
	 * @param aName   the a name
	 * @param anAlias the an alias
	 * @return the i selectable
	 */
	default ISelectable selectColumn(final String aName, final String anAlias) {
		return IColumn.builder().name(aName).table(this).select().as(anAlias).build();
	}

	/**
	 * Builder.
	 *
	 * @return the i table builder
	 */
	public static ITableBuilder builder() {
		return new TableBuilder();
	}

	/**
	 * The Interface ITableBuilder.
	 */
	public static interface ITableBuilder {

		/**
		 * Name.
		 *
		 * @param aName the a name
		 * @return the i table builder final stage
		 */
		ITableBuilderFinalStage name(String aName);
	}

	/**
	 * The Interface ITableBuilderFinalStage.
	 */
	public static interface ITableBuilderFinalStage extends IBuilder<ITable> {

		/**
		 * Alias.
		 *
		 * @param anAlias the an alias
		 * @return the i builder
		 */
		IBuilder<ITable> alias(String anAlias);
	}
}
