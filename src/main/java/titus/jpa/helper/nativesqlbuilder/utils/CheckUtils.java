package titus.jpa.helper.nativesqlbuilder.utils;

/**
 * The Class CheckUtils.
 */
public final class CheckUtils {

	/**
	 * Instantiates a new check utils.
	 */
	private CheckUtils() {
	}

	/**
	 * Checks if is null or blank.
	 *
	 * @param aString the a string
	 * @return true, if is null or blank
	 */
	public static boolean isNullOrBlank(String aString) {
		return aString == null || aString.isBlank();
	}

	/**
	 * Checks if is not null or blank.
	 *
	 * @param aString the a string
	 * @return true, if is not null or blank
	 */
	public static boolean isNotNullOrBlank(String aString) {
		return !CheckUtils.isNullOrBlank(aString);
	}

}
