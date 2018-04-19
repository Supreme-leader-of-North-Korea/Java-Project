import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 *
 * @author Li Jin Quan, Lee Jian Hao, Chen Xing Yu, Kok Jia Hui
 * @version 1.0
 */
public class GuestFileIO extends FileIO<Guest>{

	/**The name of the file which the information of the Guest will be written to and read from	 */
	final static String fileName = "Guestlist.txt";
	
	/**Creating a new file object	 */
	final static File file = new File(fileName);

	/**
	 * This method reads in from Guestlist.txt and add each Guest object into glist.
	 * 
	 * @param glist - An ArrayList which holds all the details from Guestlist.txt.
	 * @throws FileNotFoundException
	 */
	public void parseList (ArrayList<Guest> glist) throws FileNotFoundException{
		// to create file when it does not exist, else Exception will be thrown
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

			Guest temp = new Guest (arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8]);
			glist.add(temp);
		}

		myScanner.close();
	}

	/**
	 * This method will export one Guest object to the Guestlist.txt.
	 * 
	 * @param g A Guest object that is going to be exported.
	 * @param fout A PrintWriter object.
	 */
	public void export (Guest g, PrintWriter fout) {
		fout.print(g.getName() + "|");
		fout.print(g.getAddress() + "|");
		fout.print(g.getCountry() + "|");
		fout.print(g.getGender() + "|");
		fout.print(g.getNationality() + "|");
		fout.print(g.getIdentity() + "|");
		fout.print(g.getIc() + "|");
		fout.print(g.getCreditDetails() + "|");
		fout.println(g.getContact());
	}

	/**
	 * This method exports every Guest object stored in the glist to Guestlist.txt.
	 * It calls the export function repeatedly until it reaches the end of glist.
	 * 
	 * @param glist This ArrayList contains all the new updates made from the user and the existing ones.
	 * @throws FileNotFoundException
	 */
	public void exportAll (ArrayList<Guest> glist) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));

		for (Guest temp: glist) 
			export (temp, fileOut);

		fileOut.close();
	}
}
