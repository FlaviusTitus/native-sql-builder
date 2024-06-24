package titus.jpa.helper.nativesqlbuilder.select;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import titus.jpa.helper.nativesqlbuilder.base.ITable;
import titus.jpa.helper.nativesqlbuilder.select.ISelectBuilder.ISelectBuilderContext;
import titus.jpa.helper.nativesqlbuilder.utils.CheckUtils;
import titus.jpa.helper.nativesqlbuilder.utils.Constants;

@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class SelectableField implements ISelectable {

	/** The table. */
	private ITable table;

	/** The name. */
	private String name;

	/** The alias. */
	private String alias;

	public SelectableField(String aName) {
		super();
		this.name = aName;
	}

	public SelectableField(String aName, String anAlias) {
		super();
		this.name = aName;
		this.alias = anAlias;
	}

	public SelectableField(Table aTable, String aName, String anAlias) {
		super();
		this.table = aTable;
		this.name = aName;
		this.alias = anAlias;
	}

	/**
	 * Builds the native sql.
	 *
	 * @param aContext the a context
	 * @return the string
	 */
	@Override
	public String buildNativeSql(ISelectBuilderContext aContext) {
		StringBuilder builder = new StringBuilder();

		if (this.table != null && CheckUtils.isNotNullOrBlank(this.table.getAlias())) {
			builder
				.append(this.table.getAlias())
				.append(Constants.ALIAS_NAME_DELIMITER);
		}

		builder
			.append(this.name);

		if (CheckUtils.isNotNullOrBlank(this.alias)) {
			builder
				.append(Constants.ONE_BLANK)
				.append(Constants.SQL_KEYWORD__AS)
				.append(Constants.ONE_BLANK)
				.append(this.alias);
		}

		return builder.toString();
	}
}
