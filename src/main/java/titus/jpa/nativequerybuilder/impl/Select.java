package titus.jpa.nativequerybuilder.impl;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import titus.jpa.nativequerybuilder.ISelect;
import titus.jpa.nativequerybuilder.ISelectQuery;

/**
 * The Class SelectStatement.
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Select implements ISelect {

	/** The count query. */
	private String countQuery;

	/** The data query. */
	private String dataQuery;

	@Override
	public ISelectQuery build(final EntityManager anEntityManager) {
		// TODO Auto-generated method stub
		return null;
	}

}
