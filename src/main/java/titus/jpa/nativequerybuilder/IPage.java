package titus.jpa.nativequerybuilder;

public interface IPage extends INativeSql {

	int getPage();

	int getSize();

}