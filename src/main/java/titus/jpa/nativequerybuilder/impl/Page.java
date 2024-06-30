package titus.jpa.nativequerybuilder.impl;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import titus.jpa.nativequerybuilder.IPage;

/**
 * The Class Page.
 */
@Getter
@ToString
@EqualsAndHashCode
public class Page implements IPage {

	/** The page. */
	private final int page;

	/** The size. */
	private final int size;

	/** The native sql. */
	private String nativeSql;

	/**
	 * The Class PageBuilder.
	 *
	 * @param page the page
	 * @param size the size
	 */

	/**
	 * The Class PageBuilder.
	 */

	/**
	 * The Class PageBuilder.
	 */

	/**
	 * The Class PageBuilder.
	 */
	@Builder
	private Page(final int page, final int size) {
		this.page = page;
		this.size = size;
	}

	/**
	 * Builds the native sql.
	 *
	 * @param anContext the an context
	 * @return the string
	 * @see titus.jpa.nativequerybuilder.IPage#buildNativeSql(titus.jpa.nativequerybuilder.INativeSql.INativeSqlBuildContext)
	 */
	@Override
	public String buildNativeSql(final INativeSqlBuildContext anContext) {
		if (this.nativeSql == null) {
			this.nativeSql = String.format("LIMIT %d OFFSET %d", this.size, (this.page - 1) * this.size);
		}

		return this.nativeSql;
	}

}
