package titus.jpa.nativequerybuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import titus.jpa.nativequerybuilder.impl.NativeSqlBuildContext;

class IColumnTest {

	@Test
	void testGetColumnWithoutTable() {
		final String expect = "id".toUpperCase();
		final IColumn column = IColumn.builder().name("id").build();

		final String result = column.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testGetColumnBySimpleTable() {
		final String expect = "id".toUpperCase();
		final ITable table = ITable.builder().name("test").build();
		final IColumn column = table.column("id");

		final String result = column.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testGetColumnByTableWithAlias() {
		final String expect = "t.id".toUpperCase();
		final ITable table = ITable.builder().name("test").alias("t").build();
		final IColumn column = table.column("id");

		final String result = column.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

}
