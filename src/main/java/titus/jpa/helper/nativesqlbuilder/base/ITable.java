package titus.jpa.helper.nativesqlbuilder.base;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import titus.jpa.helper.Select;
import titus.jpa.helper.nativesqlbuilder.select.ISelectBuilder;
import titus.jpa.helper.nativesqlbuilder.select.ISelectBuilder.ISelectBuilderContext;
import titus.jpa.helper.nativesqlbuilder.select.SelectableField;
import titus.jpa.helper.nativesqlbuilder.utils.CheckUtils;
import titus.jpa.helper.nativesqlbuilder.utils.Constants;

/**
 * The Interface ITable.
 */

public interface ITable extends ISqlBuilder {

	public string getName();

	public Object getAlias();

	@Getter
	@ToString
	@EqualsAndHashCode
	@Builder(toBuilder = true)
	public static class Table implements ITable {

		/** The name. */
		private String name;

		/** The alias. */
		private String alias;

		/**
		 * Builds the native sql.
		 *
		 * @param aContext the a context
		 * @return the string
		 */
		@Override
		public String buildNativeSql(ISelectBuilderContext aContext) {
			StringBuilder builder = new StringBuilder();
			builder.append(this.name);

			if (CheckUtils.isNotNullOrBlank(this.alias)) {
				builder
					.append(Constants.ONE_BLANK)
					.append(this.alias);
			}

			return builder.toString();
		}

		public ISelectBuilder select() {
			return Select.builder().from(this);
		}

		public SelectableField.SelectableFieldBuilder selectField() {
			return SelectableField.builder().table(this);
		}
	}

}
