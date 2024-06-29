package titus.jpa.nativequerybuilder.impl;

/**
 * The Class Utils.
 */
public final class Utils {

	/**
	 * Instantiates a new utils.
	 */
	private Utils() {
	}

	/**
	 * Checks if is null or blank.
	 *
	 * @param aString
	 *            the a string
	 * @return true, if is null or blank
	 */
	public static boolean isNullOrBlank(final String aString) {
		return aString == null || aString.isBlank();
	}

	/**
	 * Checks if is not null or blank.
	 *
	 * @param aString
	 *            the a string
	 * @return true, if is not null or blank
	 */
	public static boolean isNotNullOrBlank(final String aString) {
		return !Utils.isNullOrBlank(aString);
	}

}
