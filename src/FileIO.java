import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
	
	//Attributes
	final static String fileName = "datalist.txt";

	public FileIO() {
		// TODO Auto-generated constructor stub
	}
	
	//Retrieve data from file
	public static void parseGuestList (ArrayList<Guest> glist) throws FileNotFoundException{
		Scanner myScanner = new Scanner (new File (fileName));
		String str;
		
		//Read title 'Guest List'
		if (myScanner.hasNextLine())
			myScanner.nextLine();
		
		while (myScanner.hasNextLine()) {
			str = myScanner.nextLine();
			String[] arr = str.split("\\|");
			
			Guest temp = new Guest (arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7]);
			glist.add(temp);
		}
		
		myScanner.close();
	}
	
	public static void export (Guest g, PrintWriter fout) {
		fout.print(g.getName() + "|");
		fout.print(g.getCreditDetails() + "|");
		fout.print(g.getAddress() + "|");
		fout.print(g.getCountry() + "|");
		fout.print(g.getGender() + "|");
		fout.print(g.getNationality() + "|");
		fout.print(g.getIdentity() + "|");
		fout.println(g.getContact());
	}
	
	public static void exportAll (ArrayList<Guest> glist) throws FileNotFoundException {
		PrintWriter fileOut = new PrintWriter (new FileOutputStream (fileName, false));
		
		fileOut.println("Guest List");
		
		for (Guest temp: glist) 
			export (temp, fileOut);
		
		fileOut.close();
	}
}
