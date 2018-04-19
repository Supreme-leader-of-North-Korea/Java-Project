import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * FileIO is an abstract class which requires all the child class to implement it's abstract method. 
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
@SuppressWarnings("hiding")
public abstract class FileIO<Class>{  

	/**
	 * This method is a abstract method which will be implemented in the child class.
	 * 
	 * @param list the sub class have to read a file and write into an ArrayList.
	 * @throws FileNotFoundException
	 */
	abstract void parseList (ArrayList<Class> list) throws FileNotFoundException;

	/**
	 * This method is a abstract method which will be implemented in the child class.
	 * 
	 * @param c A class which will be specify by the child class.
	 * @param pw A PrintWriter object.
	 */
	abstract void export (Class c,PrintWriter pw);

	/**
	 * This method is a abstract method which will be implemented in the child class.
	 * 
	 * @param list An arrayList of a specific class in which the child class will specify
	 * @throws FileNotFoundException
	 */
	abstract void exportAll (ArrayList<Class> list) throws FileNotFoundException;

}  