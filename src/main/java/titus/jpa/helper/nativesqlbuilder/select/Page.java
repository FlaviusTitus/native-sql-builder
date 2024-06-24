package titus.jpa.helper.nativesqlbuilder.select;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The Class Page.
 *
 * @param <R> the generic type
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Page<R> {

	/** The result. */
	private List<R> result;

	/** The total. */
	private long total;

}
