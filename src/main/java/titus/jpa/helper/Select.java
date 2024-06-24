package titus.jpa.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityManager;
import titus.jpa.helper.nativesqlbuilder.base.ICondition;
import titus.jpa.helper.nativesqlbuilder.base.ITable;
import titus.jpa.helper.nativesqlbuilder.select.ISelectBuilder;
import titus.jpa.helper.nativesqlbuilder.select.ISelectStatement;
import titus.jpa.helper.nativesqlbuilder.select.ISelectable;
import titus.jpa.helper.nativesqlbuilder.utils.Constants;

/**
 * The Class Select.
 */
public final class Select implements ISelectBuilder {

	/**
	 * Instantiates a new select.
	 */
	private Select() {

	}

	private boolean selectAllFields = false;

	/** The fields. */
	private List<ISelectable> fields = new ArrayList<>();

	/** The table. */
	private ITable table;

	/** The a condition. */
	private ICondition aCondition;

	/** The page number. */
	private int pageNumber;

	/** The page size. */
	private int pageSize;

	/**
	 * Builder.
	 *
	 * @return the select
	 */
	public static Select builder() {
		return new Select();
	}

	/**
	 * All.
	 *
	 * @return the i select builder
	 */
	@Override
	public ISelectBuilder selectAllFields() {
		this.selectAllFields = true;
		this.fields.isEmpty();
		return this;
	}

	/**
	 * Field.
	 *
	 * @param aField the a field
	 * @return the i select builder
	 */
	@Override
	public ISelectBuilder field(ISelectable... aField) {
		if (!this.selectAllFields) {
			this.fields.addAll(Arrays.asList(aField));
		}
		return this;
	}

	/**
	 * From.
	 *
	 * @param aTable the a table
	 * @return the i select builder
	 */
	@Override
	public ISelectBuilder from(ITable aTable) {
		this.table = aTable;
		return this;
	}

	/**
	 * Where.
	 *
	 * @param aCondition the a condition
	 * @return the i select builder
	 */
	@Override
	public ISelectBuilder where(ICondition aCondition) {
		// TODO Auto-generated method stub
		return this;
	}

	/**
	 * Group.
	 *
	 * @return the i select builder
	 */
	@Override
	public ISelectBuilder group() {
		// TODO Auto-generated method stub
		return this;
	}

	/**
	 * Order.
	 *
	 * @return the i select builder
	 */
	@Override
	public ISelectBuilder order() {
		// TODO Auto-generated method stub
		return this;
	}

	/**
	 * Paging.
	 *
	 * @return the i select builder
	 */
	@Override
	public ISelectBuilder paging() {
		// TODO Auto-generated method stub
		return this;
	}

	private String buildNativeQuery() {

		SelectBuilderContext context = new SelectBuilderContext();

		StringBuilder builder = new StringBuilder();
		builder.append(Constants.SQL_KEYWORD__SELECT).append(Constants.ONE_BLANK);

		if (this.selectAllFields) {
			builder.append(Constants.SELECT_ALL_FIELDS).append(Constants.ONE_BLANK);
		} else {
			builder
				.append(this.fields
					.stream()
					.map(field -> field.buildNativeSql(context))
					.collect(Collectors.joining(Constants.FIELD_SEPARATOR)))
				.append(Constants.ONE_BLANK);
		}

		builder
			.append(Constants.SQL_KEYWORD__FROM)
			.append(Constants.ONE_BLANK)
			.append(this.table.buildNativeSql(context));

		return builder.toString();
	}

	public static class SelectBuilderContext implements ISelectBuilderContext {

	}

	/**
	 * Builds the.
	 *
	 * @param <R>         the generic type
	 * @param aResultType the a result type
	 * @return the i select statement
	 */
	@Override
	public <R> ISelectStatement<R> build(Class<R> aResultType) {
		if (!this.selectAllFields && this.fields.isEmpty()) {
			throw new IllegalArgumentException("Select all fields or define one field to be selected!");
		}

		if (this.table == null) {
			throw new IllegalArgumentException("A table to be select from is required!");
		}

		return new SelectStatement<>(this.buildNativeQuery());
	}

	public static class SelectStatement<R> implements ISelectStatement<R> {

		private final String nativeQuery;

		private SelectStatement(String aNativeQuery) {
			this.nativeQuery = aNativeQuery;
		}

		@Override
		public Class<R> resultType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getNativeCountQuery() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getNativeQuery() {
			return this.nativeQuery;
		}

		@Override
		public ISelectQuery<R> prepare(EntityManager aEntityManager) {
			// TODO Auto-generated method stub
			return null;
		}

	};

}
