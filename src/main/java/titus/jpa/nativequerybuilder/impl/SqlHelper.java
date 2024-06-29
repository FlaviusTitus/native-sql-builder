package titus.jpa.nativequerybuilder.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public final class SqlHelper {

	private SqlHelper() {
	}

	public static String toNativeSqlValue(final Object aValue) {
		if (aValue == null)
			return SqlContants.KEYWORD_NULL;
		else if (aValue instanceof String)
			return String.format("%s%s%s", SqlContants.STRING_ESCAPE_CHAR, aValue.toString(), SqlContants.STRING_ESCAPE_CHAR);
		else if (aValue.getClass().isPrimitive())
			return aValue.toString();
		else if (aValue instanceof Boolean)
			return aValue.toString();
		else if (aValue instanceof Integer)
			return aValue.toString();
		else if (aValue instanceof Long)
			return aValue.toString();
		else if (aValue instanceof Float)
			return aValue.toString();
		else if (aValue instanceof Double)
			return aValue.toString();
		else if (aValue.getClass().isArray())
			return String.format("( %s )", Arrays
					.stream((Object[]) aValue)
					.map(SqlHelper::toNativeSqlValue)
					.collect(Collectors.joining(SqlContants.VALUE_DELIMITER)));
		else if (aValue instanceof Collection<?> collection)
			return String.format("( %s )", collection
					.stream()
					.map(SqlHelper::toNativeSqlValue)
					.collect(Collectors.joining(SqlContants.VALUE_DELIMITER)));

		throw new RuntimeException(String.format("Value type %s not supported!", aValue.getClass().getName()));
	}

}
