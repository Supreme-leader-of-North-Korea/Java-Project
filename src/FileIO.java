import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

@SuppressWarnings("hiding")
public abstract class FileIO<Class>{  

	/**
	 * 
	 * @param list
	 * @throws FileNotFoundException
	 */
	abstract void parseList (ArrayList<Class> list) throws FileNotFoundException;

	/**
	 * 
	 * @param c
	 * @param pw
	 */
	abstract void export (Class c,PrintWriter pw);

	/**
	 * 
	 * @param list
	 * @throws FileNotFoundException
	 */
	abstract void exportAll (ArrayList<Class> list) throws FileNotFoundException;

}  