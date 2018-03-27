import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileIO {

	public FileIO() {
		// TODO Auto-generated constructor stub
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
		PrintWriter fileOut = new PrintWriter ("datalist.txt");
		
		fileOut.println("Guest List");
		
		for (Guest temp: glist) 
			export (temp, fileOut);
		
		fileOut.close();
	}
}
