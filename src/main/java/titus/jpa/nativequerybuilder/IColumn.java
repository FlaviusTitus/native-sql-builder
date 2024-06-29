package titus.jpa.nativequerybuilder;

import titus.jpa.nativequerybuilder.ISelectable.ISelectableStageAliasBuilder;
import titus.jpa.nativequerybuilder.impl.Column.ColumnBuilder;

/**
 * The Interface IColumn.
 */
public interface IColumn extends INativeSql {

	default ISelectableStageAliasBuilder toSelectable() {
		return ISelectable.builder().column(this);
	}

	public static IColumnBuilder builder() {
		return new ColumnBuilder();
	}

	public static interface IColumnBuilder {

		IColumnBuilderFromTableStage name(String aName);
	}

	public static interface IColumnBuilderFromTableStage extends IBuilder<IColumn> {
		IColumnBuilderSelectOrBuildStage table(ITable aTable);
	}

	public static interface IColumnBuilderSelectOrBuildStage extends IBuilder<IColumn> {
		IColumnBuilderSelectStage select();
	}

	public static interface IColumnBuilderSelectStage extends IBuilder<ISelectable> {
		IBuilder<ISelectable> as(String anAlias);
	}

}