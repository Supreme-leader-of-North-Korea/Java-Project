import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * MenuFileIO handles input and output of data into MenuList.txt
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class MenuFileIO extends FileIO<MenuItem> {

	/**The name of the file which the information of the MenuItem will be written to and read from	 */
	final static String fileName = "MenuList.txt";

	/**Creating a new file object	 */
	final static File file = new File(fileName);

	/**
	 * This method reads in from MenuList.txt and add each MenuItem object into list.
	 * 
	 * @param list An Array List which holds all the details from MenuList.txt.
	 * @throws FileNotFoundException
	 */
	public void parseList (ArrayList<MenuItem> list) throws FileNotFoundException{
		try {
			file.createNewFile();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Scanner myScanner = new Scanner (new File (fileName));
		String str;

		while (myScanner.hasNextLine()) {
			str = myScanner.nextLine();
			String[] arr = str.split("\\|");

			MenuItem temp = new MenuItem (arr[0], arr[1], Double.parseDouble(arr[2]));
			list.add(temp);
		}

		myScanner.close();
	}

	/**
	 * This method will export one MenuItem object to a MenuList.txt.
	 * 
	 * @param m The MenuItem object that is going to be exported.
	 * @param fout A PrintWriter object.
	 */
	public void export (MenuItem m, PrintWriter fout) {
		fout.print(m.getName() + "|");
		fout.print(m.getDescription() + "|");
		fout.println(m.getPrice());

	}

	/**
	 * This method exports every MenuItem object stored in the list to MenuList.txt.
	 * It calls the export function repeatedly until it reaches the end of list.
	 * 
	 * @param list This ArrayList contains all the new updates made from the user and the existing ones.
	 * @throws FileNotFoundException
	 */
	public void exportAll (ArrayList<MenuItem> list) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));

		for (MenuItem temp: list) 
			export (temp, fileOut);

		fileOut.close();
	}
}
