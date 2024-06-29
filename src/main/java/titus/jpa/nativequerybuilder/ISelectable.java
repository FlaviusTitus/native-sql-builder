package titus.jpa.nativequerybuilder;

import titus.jpa.nativequerybuilder.impl.Selectable.SelectableBuilder;

/**
 * The Interface ISelectColumn.
 */
public interface ISelectable extends INativeSql {

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	String getAlias();

	/**
	 * Builder.
	 *
	 * @return the i selectable builder
	 */
	public static ISelectableBuilder builder() {
		return SelectableBuilder.builder();
	}

	/**
	 * The Interface ISelectableBuilder.
	 */
	public static interface ISelectableBuilder {

		/**
		 * Column.
		 *
		 * @param anColumn
		 *            the a column
		 * @return the i selectable stage alias builder
		 */
		ISelectableStageAliasBuilder column(IColumn anColumn);
	}

	/**
	 * The Interface ISelectableStageAliasBuilder.
	 */
	public static interface ISelectableStageAliasBuilder extends IBuilder<ISelectable> {

		/**
		 * As.
		 *
		 * @param anAlias
		 *            the an alias
		 * @return the i builder
		 */
		IBuilder<ISelectable> as(String anAlias);
	}

}