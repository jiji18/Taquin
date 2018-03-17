package exceptions;

public class NombreDouble extends Exception {

	public NombreDouble(int nombreEnDouble){
		super("Le nombre"+nombreEnDouble+" est ecrit deux fois");
	}
}
