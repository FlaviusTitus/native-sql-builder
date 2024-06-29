package titus.jpa.nativequerybuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import titus.jpa.nativequerybuilder.impl.NativeSqlBuildContext;

class IConditionTest {

	public static String TEST_EXPRESSION_TEXT = "expression".toUpperCase();
	public static String TEST_EXPRESSION_SIMPLE_NOT = String
		.format("not %s", IConditionTest.TEST_EXPRESSION_TEXT)
		.toUpperCase();
	public static String TEST_EXPRESSION_SIMPLE_AND = String
		.format("%1$s and %1$s", IConditionTest.TEST_EXPRESSION_TEXT)
		.toUpperCase();
	public static String TEST_EXPRESSION_SIMPLE_OR = String
		.format("%1$s or %1$s", IConditionTest.TEST_EXPRESSION_TEXT)
		.toUpperCase();

	public static IExpression TEST_EXPRESSION = new IExpression() {

		@Override
		public String buildNativeSql(final INativeSqlBuildContext anContext) {
			return IConditionTest.TEST_EXPRESSION_TEXT;
		}
	};

	@Test
	void testSingleCondition() {
		final ICondition condition = ICondition
			.builder()
			.expression(IConditionTest.TEST_EXPRESSION)
			.build();

		final String result = condition.buildNativeSql(new NativeSqlBuildContext());
		Assertions.assertEquals(IConditionTest.TEST_EXPRESSION_TEXT, result);
	}

	@Test
	void testSimpleNotCondition() {
		final ICondition condition = ICondition
			.builder()
			.not()
			.expression(IConditionTest.TEST_EXPRESSION)
			.build();

		final String result = condition.buildNativeSql(new NativeSqlBuildContext());
		Assertions.assertEquals(IConditionTest.TEST_EXPRESSION_SIMPLE_NOT, result);
	}

	@Test
	void testSimpleAndCondition() {
		final ICondition condition = ICondition
			.builder()
			.expression(IConditionTest.TEST_EXPRESSION)
			.and()
			.expression(IConditionTest.TEST_EXPRESSION)
			.build();

		final String result = condition.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();
		Assertions.assertEquals(IConditionTest.TEST_EXPRESSION_SIMPLE_AND, result);
	}

	@Test
	void testSimpleOrCondition() {
		final ICondition condition = ICondition
			.builder()
			.expression(IConditionTest.TEST_EXPRESSION)
			.or()
			.expression(IConditionTest.TEST_EXPRESSION)
			.build();

		final String result = condition.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();
		Assertions.assertEquals(IConditionTest.TEST_EXPRESSION_SIMPLE_OR, result);
	}

	@Test
	void testSimpleGroupWithAndCondition() {
		final String expected = String.format("( %s )", IConditionTest.TEST_EXPRESSION_SIMPLE_AND).toUpperCase();
		final ICondition condition = ICondition
			.builder()
			.group(ICondition
				.builder()
				.expression(IConditionTest.TEST_EXPRESSION)
				.and()
				.expression(IConditionTest.TEST_EXPRESSION)
				.build())
			.build();

		final String result = condition.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();
		Assertions.assertEquals(expected, result);
	}

	@Test
	void testNotGroupWithAndCondition() {
		final String expected = String.format("not ( %s )", IConditionTest.TEST_EXPRESSION_SIMPLE_AND).toUpperCase();
		final ICondition condition = ICondition
			.builder()
			.not()
			.group(ICondition
				.builder()
				.expression(IConditionTest.TEST_EXPRESSION)
				.and()
				.expression(IConditionTest.TEST_EXPRESSION)
				.build())
			.build();

		final String result = condition.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();
		Assertions.assertEquals(expected, result);
	}

	@Test
	void testAndGroupsWithAndCondition() {
		final String expected = String
			.format("( %1$s ) and ( %1$s )", IConditionTest.TEST_EXPRESSION_SIMPLE_AND)
			.toUpperCase();
		final ICondition condition = ICondition
			.builder()
			.group(ICondition
				.builder()
				.expression(IConditionTest.TEST_EXPRESSION)
				.and()
				.expression(IConditionTest.TEST_EXPRESSION)
				.build())
			.and()
			.group(ICondition
				.builder()
				.expression(IConditionTest.TEST_EXPRESSION)
				.and()
				.expression(IConditionTest.TEST_EXPRESSION)
				.build())
			.build();

		final String result = condition.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();
		Assertions.assertEquals(expected, result);
	}

	@Test
	void testAndGroupsWithOrCondition() {
		final String expected = String
			.format("( %1$s ) or ( %1$s )", IConditionTest.TEST_EXPRESSION_SIMPLE_AND)
			.toUpperCase();
		final ICondition condition = ICondition
			.builder()
			.group(ICondition
				.builder()
				.expression(IConditionTest.TEST_EXPRESSION)
				.and()
				.expression(IConditionTest.TEST_EXPRESSION)
				.build())
			.or()
			.group(ICondition
				.builder()
				.expression(IConditionTest.TEST_EXPRESSION)
				.and()
				.expression(IConditionTest.TEST_EXPRESSION)
				.build())
			.build();

		final String result = condition.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();
		Assertions.assertEquals(expected, result);
	}

	@Test
	void testGroupInGroupCondition() {
		final String expected = String
			.format("( ( %1$s ) and ( %1$s ) )", IConditionTest.TEST_EXPRESSION_SIMPLE_AND)
			.toUpperCase();
		final ICondition condition = ICondition
			.builder()
			.group(ICondition
				.builder()
				.group(ICondition
					.builder()
					.expression(IConditionTest.TEST_EXPRESSION)
					.and()
					.expression(IConditionTest.TEST_EXPRESSION)
					.build())
				.and()
				.group(ICondition
					.builder()
					.expression(IConditionTest.TEST_EXPRESSION)
					.and()
					.expression(IConditionTest.TEST_EXPRESSION)
					.build())
				.build())
			.build();

		final String result = condition.buildNativeSql(new NativeSqlBuildContext()).toUpperCase();
		Assertions.assertEquals(expected, result);
	}
}
