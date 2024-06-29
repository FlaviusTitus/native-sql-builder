package titus.jpa.nativequerybuilder;

import titus.jpa.nativequerybuilder.impl.Table.TableBuilder;

public interface ITable extends INativeSql, ISelectFrom {

	String getAlias();

	String getName();

	default IColumn column(final String aName) {
		return IColumn.builder().name(aName).table(this).build();
	}

	default ISelectable selectColumn(final String aName) {
		return IColumn.builder().name(aName).table(this).select().build();
	}

	default ISelectable selectColumn(final String aName, final String anAlias) {
		return IColumn.builder().name(aName).table(this).select().as(anAlias).build();
	}

	public static ITableBuilder builder() {
		return new TableBuilder();
	}

	public static interface ITableBuilder {
		ITableBuilderFinalStage name(String aName);
	}

	public static interface ITableBuilderFinalStage extends IBuilder<ITable> {

		IBuilder<ITable> alias(String anAlias);
	}
}