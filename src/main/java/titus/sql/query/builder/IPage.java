package titus.sql.query.builder;

/**
 * The Interface IPage.
 */
public interface IPage extends INativeSql {

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	int getPage();

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	int getSize();

}
