package titus.jpa.nativequerybuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import titus.jpa.nativequerybuilder.impl.NativeSqlBuildContext;

class ITableTest {

	private static final ITable TEST_TABLE = ITable.builder().name("test").build();
	private static final ITable TEST_TABLE_WITH_ALIAS = ITable.builder().name("test").alias("t").build();

	@Test
	void testSimpleTable() {
		final String expect = "test".toUpperCase();
		final String result = ITableTest.TEST_TABLE.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testTableWithAlias() {
		final String expect = "test t".toUpperCase();
		final String result = ITableTest.TEST_TABLE_WITH_ALIAS
			.buildNativeSql(new NativeSqlBuildContext())
			.toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testGetColumFromTable() {
		IColumn result = ITableTest.TEST_TABLE.column("id");
		Assertions.assertNotNull(result);
	}

	@Test
	void testSelectColumFromTable() {
		ISelectable result = ITableTest.TEST_TABLE.selectColumn("id");
		Assertions.assertNotNull(result);
	}

	@Test
	void testSelectColumWithAliasFromTable() {
		ISelectable result = ITableTest.TEST_TABLE.selectColumn("fullname", "name");
		Assertions.assertNotNull(result);
		Assertions.assertEquals("name", result.getAlias());
	}

}
