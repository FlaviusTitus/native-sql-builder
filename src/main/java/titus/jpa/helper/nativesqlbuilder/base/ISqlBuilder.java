package titus.jpa.helper.nativesqlbuilder.base;

import titus.jpa.helper.nativesqlbuilder.select.ISelectBuilder.ISelectBuilderContext;

public interface ISqlBuilder {

	String buildNativeSql(ISelectBuilderContext aContext);
}
