package titus.sql.query.builder;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import titus.sql.query.builder.IColumn;
import titus.sql.query.builder.IExpression;
import titus.sql.query.builder.ISelect;
import titus.sql.query.builder.ITable;
import titus.sql.query.builder.impl.NativeSqlBuildContext;

class IExpressionTest {

	public static final ITable TEST_TABLE = ITable.builder().name("test").alias("t").build();
	public static final IColumn TEST_COLUMN_A = IExpressionTest.TEST_TABLE.column("id");
	public static final IColumn TEST_COLUMN_B = IExpressionTest.TEST_TABLE.column("id");
	public static final String TEST_TABLE_STRING = IExpressionTest.TEST_TABLE
		.buildNativeSql(new NativeSqlBuildContext());
	public static final String TEST_COLUMN_A_STRING = IExpressionTest.TEST_COLUMN_A
		.buildNativeSql(new NativeSqlBuildContext());
	public static final String TEST_COLUMN_B_STRING = IExpressionTest.TEST_COLUMN_B
		.buildNativeSql(new NativeSqlBuildContext());

	@Test
	void testEqExpression() {
		final String expect = String.format("%s = 'test'", IExpressionTest.TEST_COLUMN_A_STRING).toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.eq()
			.value("test")
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testNeqExpression() {
		final String expect = String.format("%s <> 'test'", IExpressionTest.TEST_COLUMN_A_STRING).toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.neq()
			.value("test")
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testGtExpression() {
		final String expect = String.format("%s > 1", IExpressionTest.TEST_COLUMN_A_STRING).toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.gt()
			.value(1)
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testGteExpression() {
		final String expect = String.format("%s >= 1", IExpressionTest.TEST_COLUMN_A_STRING).toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.gte()
			.value(1)
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testLtExpression() {
		final String expect = String.format("%s < 1", IExpressionTest.TEST_COLUMN_A_STRING).toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.lt()
			.value(1)
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testLteExpression() {
		final String expect = String.format("%s <= 1", IExpressionTest.TEST_COLUMN_A_STRING).toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.lte()
			.value(1)
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testAnyExpression() {
		final String expect = String
			.format("%s any ( 'test', 'test' )", IExpressionTest.TEST_COLUMN_A_STRING)
			.toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.any()
			.values(Arrays.asList("test", "test"))
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testInExpression() {
		final String expect = String
			.format("%s in ( 'test', 'test' )", IExpressionTest.TEST_COLUMN_A_STRING)
			.toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.in()
			.values(Arrays.asList("test", "test"))
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testAllExpression() {
		final String expect = String
			.format("%s all ( 'test', 'test' )", IExpressionTest.TEST_COLUMN_A_STRING)
			.toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.all()
			.values(Arrays.asList("test", "test"))
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testSomeExpression() {
		final String expect = String
			.format("%s some ( 'test', 'test' )", IExpressionTest.TEST_COLUMN_A_STRING)
			.toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.some()
			.values(Arrays.asList("test", "test"))
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testExistsExpression() {
		final String expect = String
			.format("%s exists ( 'test', 'test' )", IExpressionTest.TEST_COLUMN_A_STRING)
			.toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.exists()
			.values(Arrays.asList("test", "test"))
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testLikeExpression() {
		final String expect = String.format("%s like 'test'", IExpressionTest.TEST_COLUMN_A_STRING).toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.like()
			.value("test")
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testIlikeExpression() {
		final String expect = String.format("%s ilike 'test'", IExpressionTest.TEST_COLUMN_A_STRING).toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.ilike()
			.value("test")
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		Assertions.assertEquals(expect, result);
	}

	@Test
	void testInBySubqueryExpression() {
		final String expect = String
			.format("%1$s in ( select %1$s from %2$s )", IExpressionTest.TEST_COLUMN_A_STRING,
					IExpressionTest.TEST_TABLE_STRING)
			.toUpperCase();

		final IExpression expression = IExpression
			.builder()
			.column(IExpressionTest.TEST_COLUMN_A)
			.in()
			.subSelect(ISelect
				.builder()
				.columns(IExpressionTest.TEST_COLUMN_A.toSelectable().build())
				.from(IExpressionTest.TEST_TABLE)
				.buildAsSubSelect())
			.build();

		final String result = expression.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();

		System.out.println(result);

		Assertions.assertEquals(expect, result);
	}
}
