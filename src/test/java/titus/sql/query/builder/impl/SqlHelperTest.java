package titus.sql.query.builder.impl;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import titus.sql.query.builder.impl.SqlContants;
import titus.sql.query.builder.impl.SqlHelper;

class SqlHelperTest {

	@Test
	void testToNativeSqlValueWithNull() {
		final String expect = SqlContants.KEYWORD_NULL.toUpperCase();
		String result = SqlHelper.toNativeSqlValue(null).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueWithString() {
		final String expect = "'test'".toUpperCase();
		String result = SqlHelper.toNativeSqlValue("test").toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueWithEmptyString() {
		final String expect = "''".toUpperCase();
		String result = SqlHelper.toNativeSqlValue("").toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueBooleanTrue() {
		final String expect = "true".toUpperCase();
		String result = SqlHelper.toNativeSqlValue(true).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueBooleanFalse() {
		final String expect = "false".toUpperCase();
		String result = SqlHelper.toNativeSqlValue(false).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueInteger() {
		final String expect = "1".toUpperCase();
		String result = SqlHelper.toNativeSqlValue(1).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueLong() {
		final String expect = "1".toUpperCase();
		String result = SqlHelper.toNativeSqlValue(1L).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueFloat() {
		final String expect = "1.0".toUpperCase();
		String result = SqlHelper.toNativeSqlValue(1.0F).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueDouble() {
		final String expect = "1.0".toUpperCase();
		String result = SqlHelper.toNativeSqlValue(1.0D).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueWithNullArray() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", SqlContants.KEYWORD_NULL).toUpperCase();
		String result = SqlHelper.toNativeSqlValue(new Object[] { null, null, null }).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueWithStringArray() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "'test'").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(new String[] { "test", "test", "test" }).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueWithEmptyStringArray() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "''").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(new String[] { "", "", "" }).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueBooleanTrueArray() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "true").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(new Boolean[] { true, true, true }).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueBooleanFalseArray() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "false").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(new Boolean[] { false, false, false }).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueIntegerArray() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "1").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(new Integer[] { 1, 1, 1 }).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueLongArray() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "1").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(new Long[] { 1L, 1L, 1L }).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueFloatArray() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "1.0").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(new Float[] { 1.0F, 1.0F, 1.0F }).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueDoubleArray() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "1.0").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(new Double[] { 1.0D, 1.0D, 1.0D }).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueWithNullCollection() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", SqlContants.KEYWORD_NULL).toUpperCase();
		String result = SqlHelper.toNativeSqlValue(Arrays.asList(null, null, null)).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueWithStringCollection() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "'test'").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(Arrays.asList("test", "test", "test")).toUpperCase();
		Assertions.assertEquals(expect, result);

	}

	@Test
	void testToNativeSqlValueWithEmptyStringCollection() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "''").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(Arrays.asList("", "", "")).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueBooleanTrueCollection() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "true").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(Arrays.asList(true, true, true)).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueBooleanFalseCollection() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "false").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(Arrays.asList(false, false, false)).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueIntegerCollection() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "1").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(Arrays.asList(1, 1, 1)).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueLongCollection() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "1").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(Arrays.asList(1L, 1L, 1L)).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueFloatCollection() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "1.0").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(Arrays.asList(1.0F, 1.0F, 1.0F)).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

	@Test
	void testToNativeSqlValueDoubleCollection() {
		final String expect = String.format("( %1$s, %1$s, %1$s )", "1.0").toUpperCase();
		String result = SqlHelper.toNativeSqlValue(Arrays.asList(1.0D, 1.0D, 1.0D)).toUpperCase();
		Assertions.assertEquals(expect, result);
	}

}
