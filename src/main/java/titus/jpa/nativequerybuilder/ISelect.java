package titus.jpa.nativequerybuilder;

import jakarta.persistence.EntityManager;
import titus.jpa.nativequerybuilder.impl.SelectBuilder;

/**
 * The Interface ISelectStatement.
 */
public interface ISelect {

	String getDataQuery();

	String getCountQuery();

	public static ISelectBuilder builder() {
		return SelectBuilder.builder();
	}

	public ISelectQuery build(EntityManager anEntityManager);

}