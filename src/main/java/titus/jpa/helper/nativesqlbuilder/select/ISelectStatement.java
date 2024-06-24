package titus.jpa.helper.nativesqlbuilder.select;

import java.util.List;

import jakarta.persistence.EntityManager;

public interface ISelectStatement<R> {

	public Class<R> resultType();

	public String getNativeCountQuery();

	public String getNativeQuery();

	public ISelectQuery<R> prepare(EntityManager aEntityManager);

	public static interface ISelectQuery<R> {

		ISelectQuery<R> parameter(int aPosition, Object aValue);

		ISelectQuery<R> parameter(String aName, Object aValue);

		List<R> list();

		List<R> count();

		Page<R> page();

	}

}
