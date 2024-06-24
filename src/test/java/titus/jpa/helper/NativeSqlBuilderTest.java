package titus.jpa.helper;

import org.junit.jupiter.api.Test;

import titus.jpa.helper.nativesqlbuilder.base.ITable.Table;
import titus.jpa.helper.nativesqlbuilder.select.ISelectStatement;

class NativeSqlBuilderTest {

	@Test
	void testCreateSelect() {

		Table table = Table.builder().name("user").alias("u").build();

		ISelectStatement<Object> statement = table
			.select()
			.field(table.selectField().name("id").build())
			.field(table.selectField().name("fullname").alias("name").build())
			.build(null);

		System.out.println(statement.getNativeQuery());
		System.out.println(statement.getNativeCountQuery());
	}

}
