package titus.sql.query.builder;

import titus.sql.query.builder.ISelectable.ISelectableStageAliasBuilder;
import titus.sql.query.builder.impl.Column.ColumnBuilder;

/**
 * The Interface IColumn.
 */
public interface IColumn extends INativeSql {

	/**
	 * To selectable.
	 *
	 * @return the i selectable stage alias builder
	 */
	default ISelectableStageAliasBuilder toSelectable() {
		return ISelectable.builder().column(this);
	}

	/**
	 * Builder.
	 *
	 * @return the i column builder
	 */
	public static IColumnBuilder builder() {
		return new ColumnBuilder();
	}

	/**
	 * The Interface IColumnBuilder.
	 */
	public static interface IColumnBuilder {

		/**
		 * Name.
		 *
		 * @param aName the a name
		 * @return the i column builder from table stage
		 */
		IColumnBuilderFromTableStage name(String aName);
	}

	/**
	 * The Interface IColumnBuilderFromTableStage.
	 */
	public static interface IColumnBuilderFromTableStage extends IBuilder<IColumn> {

		/**
		 * Table.
		 *
		 * @param aTable the a table
		 * @return the i column builder select or build stage
		 */
		IColumnBuilderSelectOrBuildStage table(ITable aTable);
	}

	/**
	 * The Interface IColumnBuilderSelectOrBuildStage.
	 */
	public static interface IColumnBuilderSelectOrBuildStage extends IBuilder<IColumn> {

		/**
		 * Select.
		 *
		 * @return the i column builder select stage
		 */
		IColumnBuilderSelectStage select();
	}

	/**
	 * The Interface IColumnBuilderSelectStage.
	 */
	public static interface IColumnBuilderSelectStage extends IBuilder<ISelectable> {

		/**
		 * As.
		 *
		 * @param anAlias the an alias
		 * @return the i builder
		 */
		IBuilder<ISelectable> as(String anAlias);
	}

}
