package titus.jpa.helper.nativesqlbuilder.select;

import titus.jpa.helper.nativesqlbuilder.base.ICondition;
import titus.jpa.helper.nativesqlbuilder.base.ITable;

public interface ISelectBuilder {

	public static interface ISelectBuilderContext {

	}

	ISelectBuilder selectAllFields();

	ISelectBuilder field(ISelectable... aField);

	ISelectBuilder from(ITable aTable);

	ISelectBuilder where(ICondition aCondition);

	ISelectBuilder group();

	ISelectBuilder order();

	ISelectBuilder paging();

	<R> ISelectStatement<R> build(Class<R> aResultType);

}
