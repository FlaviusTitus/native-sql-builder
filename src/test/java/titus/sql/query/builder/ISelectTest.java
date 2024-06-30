package titus.sql.query.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import titus.sql.query.builder.ICondition;
import titus.sql.query.builder.IExpression;
import titus.sql.query.builder.ISelect;
import titus.sql.query.builder.ITable;

class ISelectTest {

	@Test
	void testSimpleSelectWithStrings() {
		String expectedDataQuery = "SELECT id FROM user".toUpperCase();
		String expectedCountQuery = "SELECT count( * ) FROM user".toUpperCase();

		final ISelect statement = ISelect
			.builder()
			.columns("id")
			.from("user")
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}

	@Test
	void testSimpleSelectOneColumns() {
		String expectedDataQuery = "SELECT u.id FROM user u".toUpperCase();
		String expectedCountQuery = "SELECT count( * ) FROM user u".toUpperCase();

		final ITable table = ITable
			.builder()
			.name("user")
			.alias("u")
			.build();

		final ISelect statement = ISelect
			.builder()
			.columns(table.selectColumn("id"))
			.from(table)
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}

	@Test
	void testSimpleSelectDistinctOneColumns() {
		String expectedDataQuery = "SELECT distinct u.id FROM user u".toUpperCase();
		String expectedCountQuery = "SELECT count( distinct * ) FROM user u".toUpperCase();

		final ITable table = ITable
			.builder()
			.name("user")
			.alias("u")
			.build();

		final ISelect statement = ISelect
			.builder()
			.distinct()
			.columns(table.selectColumn("id"))
			.from(table)
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}

	@Test
	void testSimpleSelectTowColumns() {
		String expectedDataQuery = "SELECT u.id, u.fullname AS name FROM user u".toUpperCase();
		String expectedCountQuery = "SELECT count( * ) FROM user u".toUpperCase();

		final ITable table = ITable
			.builder()
			.name("user")
			.alias("u")
			.build();

		final ISelect statement = ISelect
			.builder()
			.columns(
					table.selectColumn("id"),
					table.selectColumn("fullname", "name"))
			.from(table)
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}

	@Test
	void testSimpleSelectDistinctTowColumns() {
		String expectedDataQuery = "SELECT distinct u.id, u.fullname AS name FROM user u".toUpperCase();
		String expectedCountQuery = "SELECT count( distinct * ) FROM user u".toUpperCase();

		final ITable table = ITable
			.builder()
			.name("user")
			.alias("u")
			.build();

		final ISelect statement = ISelect
			.builder()
			.distinct()
			.columns(
					table.selectColumn("id"),
					table.selectColumn("fullname", "name"))
			.from(table)
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}

	@Test
	void testSimpleSelectAllColumns() {
		String expectedDataQuery = "SELECT * FROM user u".toUpperCase();
		String expectedCountQuery = "SELECT count( * ) FROM user u".toUpperCase();

		final ITable table = ITable
			.builder()
			.name("user")
			.alias("u")
			.build();

		final ISelect statement = ISelect
			.builder()
			.allColumns()
			.from(table)
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}

	@Test
	void testSimpleSelectDistinctAllColumns() {
		String expectedDataQuery = "SELECT distinct * FROM user u".toUpperCase();
		String expectedCountQuery = "SELECT count( distinct * ) FROM user u".toUpperCase();

		final ITable table = ITable
			.builder()
			.name("user")
			.alias("u")
			.build();

		final ISelect statement = ISelect
			.builder()
			.distinct()
			.allColumns()
			.from(table)
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}

	@Test
	void testSimpleSelectAllColumnsFromTable() {
		String expectedDataQuery = "SELECT u.* FROM user u".toUpperCase();
		String expectedCountQuery = "SELECT count( * ) FROM user u".toUpperCase();

		final ITable table = ITable
			.builder()
			.name("user")
			.alias("u")
			.build();

		final ISelect statement = ISelect
			.builder()
			.allColumnsFromTable(table)
			.from(table)
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}

	@Test
	void testSimpleSelectDistinctAllColumnsFromTable() {
		String expectedDataQuery = "SELECT distinct u.* FROM user u".toUpperCase();
		String expectedCountQuery = "SELECT count( distinct * ) FROM user u".toUpperCase();

		final ITable table = ITable
			.builder()
			.name("user")
			.alias("u")
			.build();

		final ISelect statement = ISelect
			.builder()
			.distinct()
			.allColumnsFromTable(table)
			.from(table)
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}

	@Test
	void testSimpleSelectWithWhere() {
		String expectedDataQuery = "SELECT u.id FROM user u where u.id = 1".toUpperCase();
		String expectedCountQuery = "SELECT count( * ) FROM user u where u.id = 1".toUpperCase();

		final ITable table = ITable
			.builder()
			.name("user")
			.alias("u")
			.build();

		final ISelect statement = ISelect
			.builder()
			.columns(
					table.selectColumn("id"))
			.from(table)
			.where(ICondition
				.builder()
				.expression(IExpression
					.builder()
					.column(table.column("id"))
					.eq()
					.value(1)
					.build())
				.build())
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}

	@Test
	void testSimpleSelectWithPage() {
		String expectedDataQuery = "SELECT * FROM user u limit 10 offset 10".toUpperCase();
		String expectedCountQuery = "SELECT count( * ) FROM user u".toUpperCase();

		final ITable table = ITable
			.builder()
			.name("user")
			.alias("u")
			.build();

		final ISelect statement = ISelect
			.builder()
			.allColumns()
			.from(table)
			.page(2, 10)
			.build();

		String resultDataQuery = statement.getDataQuery();
		String resultCountQuery = statement.getCountQuery();

		Assertions.assertEquals(expectedDataQuery, resultDataQuery);
		Assertions.assertEquals(expectedCountQuery, resultCountQuery);
	}
}
