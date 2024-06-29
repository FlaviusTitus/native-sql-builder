package titus.jpa.nativequerybuilder;

public interface INativeSql {

	public static interface INativeSqlBuildContext {
	}

	String buildNativeSql(INativeSqlBuildContext anContext);
}
