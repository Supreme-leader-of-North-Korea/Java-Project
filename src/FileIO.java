import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

@SuppressWarnings("hiding")
public abstract class FileIO<Class>{  

	abstract void parseList (ArrayList<Class> list) throws FileNotFoundException;
	abstract void export (Class c,PrintWriter pw);
	abstract void exportAll (ArrayList<Class> list) throws FileNotFoundException;

}  