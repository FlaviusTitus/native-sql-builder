package titus.sql.query.builder;

/**
 * The Interface ISelectBuilder.
 */
public interface ISelectBuilder {

	/**
	 * Distinct.
	 *
	 * @return the i select builder
	 */
	ISelectBuilder distinct();

	/**
	 * Columns.
	 *
	 * @param aColumns the a columns
	 * @return the i select builder
	 */
	ISelectFromBuilder columns(ISelectable... aColumns);

	/**
	 * Columns.
	 *
	 * @param aColumns the a columns
	 * @return the i select from builder
	 */
	ISelectFromBuilder columns(String... aColumns);

	/**
	 * All columns.
	 *
	 * @return the i select builder
	 */
	ISelectFromBuilder allColumns();

	/**
	 * All columns from table.
	 *
	 * @param aTable the a table
	 * @return the i select from builder
	 */
	ISelectFromBuilder allColumnsFromTable(ITable aTable);

	/**
	 * The Interface ISelectFromBuilder.
	 */
	public static interface ISelectFromBuilder {

		/**
		 * Table.
		 *
		 * @param aTable the a table
		 * @return the i select where builder
		 */
		ISelectWhereBuilder from(ISelectFrom aTable);

		/**
		 * From.
		 *
		 * @param aTable the a table
		 * @return the i select where builder
		 */
		ISelectWhereBuilder from(String aTable);

	}

	/**
	 * The Interface ISelectWhereBuilder.
	 */
	public static interface ISelectWhereBuilder extends ISelectPageBuilder, ISelectBuilderFinalStage {

		/**
		 * Where.
		 *
		 * @param theCondition the the condition
		 * @return the i select where builder
		 */
		ISelectWhereBuilder where(ICondition theCondition);

		/**
		 * Group by.
		 *
		 * @return the i select group by builder
		 */
		ISelectGroupByBuilder groupBy();

		/**
		 * Order by.
		 *
		 * @return the i select order by builder
		 */
		ISelectOrderByBuilder orderBy();
	}

	/**
	 * The Interface ISelectGroupByBuilder.
	 */
	public static interface ISelectGroupByBuilder extends ISelectPageBuilder, ISelectBuilderFinalStage {

		/**
		 * Order by.
		 *
		 * @return the i select order by builder
		 */
		ISelectOrderByBuilder orderBy();
	}

	/**
	 * The Interface ISelectOrderByBuilder.
	 */
	public static interface ISelectOrderByBuilder extends ISelectPageBuilder, ISelectBuilderFinalStage {

		/**
		 * Page. Page is base of one (1)
		 *
		 * @return the i select page builder
		 */

	}

	/**
	 * The Interface ISelectPageBuilder.
	 */
	public static interface ISelectPageBuilder extends ISelectBuilderFinalStage {

		/**
		 * Page.
		 *
		 * @param aPage the a page
		 * @param aSize the a size
		 * @return the i builder
		 */
		IBuilder<ISelect> page(int aPage, int aSize);
	}

	/**
	 * The Interface ISelectBuilderFinalStage.
	 */
	public static interface ISelectBuilderFinalStage extends IBuilder<ISelect> {

		/**
		 * Builds the as sub select.
		 *
		 * @return the i sub select
		 */
		ISubSelect buildAsSubSelect();
	}
}
